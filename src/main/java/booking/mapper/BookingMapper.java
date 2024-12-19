package booking.mapper;

import booking.dto.BookingDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import booking.model.Booking;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingMapper {
    public static BookingDto toBookingDto(Booking booking) {
        return new BookingDto(booking.getStart(),
                booking.getEnd(),
                booking.getItem(),
                booking.getStatus());

    }
}