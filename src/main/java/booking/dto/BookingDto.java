package booking.dto;

import booking.BookingStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {
    final LocalDate start;
    final LocalDate end;
    final long item;
    final BookingStatus status;
}