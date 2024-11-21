package webApp.travers.serviceTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import webApp.travers.domain.TravelRecord;
import webApp.travers.domain.User;
import webApp.travers.repository.TravelRecordRepository;
import webApp.travers.service.TravelRecordService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TravelRecordServiceTest {

    @InjectMocks
    private TravelRecordService service;

    @Mock
    private TravelRecordRepository repository;

    private User user;
    private AutoCloseable closeable;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1L)
                .username("testuser")
                .password("password")
                .build();
    }
    @AfterEach
    public void tearDown() throws Exception {
        closeable.close(); // 자원 해제
    }

    @Test
    public void testGetTravelRecordsByUser() {
        TravelRecord record = TravelRecord.builder()
                .user(user)
                .title("Test Record")
                .description("Description")
                .imageUrl("/images/test.jpg")
                .build();

        when(repository.findByUserId(user.getId())).thenReturn(Collections.singletonList(record));

        List<TravelRecord> records = service.getTravelRecordsByUser(user.getId());

        assertThat(records).isNotEmpty();
        assertThat(records.get(0).getTitle()).isEqualTo("Test Record");
        verify(repository, times(1)).findByUserId(user.getId());
    }
}
