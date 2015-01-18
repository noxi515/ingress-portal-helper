package jp.noxi.ingress.service;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import jp.noxi.ingress.data.entity.Portal;
import jp.noxi.ingress.data.json.JsonGameEntities;
import jp.noxi.ingress.data.json.JsonPortal;
import jp.noxi.ingress.repository.PortalRepository;
import jp.noxi.persistence.geometry.Point;
import jp.noxi.util.DateTimeUtils;

@RunWith(MockitoJUnitRunner.class)
public class PortalServiceImplTest {

    @Mock
    private PortalRepository repository;
    private PortalServiceImpl service;

    @Before
    public void before() {
        service = new PortalServiceImpl();
        service.repository = repository;
    }

    @After
    public void after() {
        DateTimeUtils.resetCurrentDateTime();
    }

    //<editor-fold desc="addJson">

    @Test
    public void testAddJson() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeUtils.setCurrentDateTime(() -> dateTime);

        List<UUID> ids = IntStream.range(0, 3).mapToObj(i -> UUID.randomUUID()).collect(toList());
        List<JsonPortal> jsonPortals =
                IntStream.range(0, 3)
                         .mapToObj(i -> new JsonPortal(ids.get(i).toString().replace("-", "") + ".16",
                                                       "title" + i, "image" + i, 10 + i, 100 + i))
                         .collect(toList());

        when(repository.getIds()).thenReturn(Arrays.asList(ids.get(2)));
        when(repository.findOne(eq(ids.get(2)))).thenReturn(new Portal());

        ArgumentCaptor<Portal> captor = ArgumentCaptor.forClass(Portal.class);
        when(repository.save(captor.capture())).thenReturn(null);

        service.initialize();
        service.addJson(new JsonGameEntities(jsonPortals));

        List<Portal> result = captor.getAllValues();
        assertThat(result, hasSize(2));

        Portal[] expected = IntStream.range(0, 2)
                                     .mapToObj(i -> new Portal(ids.get(i), "title" + i, "image" + i,
                                                               new Point((10 + i) / 1000000d, (100 + i) / 1000000d),
                                                               dateTime))
                                     .toArray(Portal[]::new);
        assertThat(result, containsInAnyOrder(expected));
    }

    //</editor-fold>


    //<editor-fold desc="findOne">

    @Test
    public void testFindOne_exists() throws Exception {
        when(repository.findOne(any())).thenReturn(new Portal());
        Optional<Portal> result = service.findOne(UUID.randomUUID());

        assertThat(result.isPresent(), is(true));
    }

    @Test
    public void testFindOne_not_exists() throws Exception {
        when(repository.findOne(any())).thenReturn(null);
        Optional<Portal> result = service.findOne(UUID.randomUUID());

        assertThat(result.isPresent(), is(false));
    }

    //</editor-fold>

}