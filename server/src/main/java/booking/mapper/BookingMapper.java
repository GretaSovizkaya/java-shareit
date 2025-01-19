package booking.mapper;

import booking.dto.BookingDto;
import booking.dto.OutputBookingDto;
import booking.model.Booking;
import item.mapper.ItemMapper;
import user.mapper.UserMapper;

public class BookingMapper {
    public static BookingDto toBookingDto(Booking booking) {
        return BookingDto.builder()
                .start(booking.getStart())
                .end(booking.getEnd())
                .itemId(booking.getItem().getId())
                .booker(booking.getBooker().getId())
                .status(booking.getStatus())
                .build();

    }

    public static OutputBookingDto toOutputBookingDto(Booking booking) {
        return OutputBookingDto.builder()
                .id(booking.getId())
                .booker(UserMapper.toUserDto(booking.getBooker()))
                .start(booking.getStart())
                .end(booking.getEnd())
                .item(ItemMapper.toItemDto(booking.getItem()))
                .status(booking.getStatus())
                .build();
    }
}