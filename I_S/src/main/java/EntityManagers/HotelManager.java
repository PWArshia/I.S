package EntityManagers;

import Common.Commons;
import Common.Members;
import Common.Reserve;
import Common.Rooms;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class HotelManager {
    private MemberManager memberManager;
    private RoomManager roomManager;
    private ReserveManager reservationManager;

    public HotelManager() throws IOException {
        this.memberManager = new MemberManager("members.txt");
        this.roomManager = new RoomManager("rooms.txt");
        this.reservationManager = new ReserveManager("reservations.txt", memberManager, roomManager);
        this.reservationManager.updateExpiredReservations(); // بررسی رزروهای منقضی‌شده در شروع
    }

    // -----------------------------------------------------> Member actions

    public boolean addMember(Members member) throws IOException {
        if (memberManager.SearchMember(member.GetID()) != null) {
            System.out.println("خطا: کاربری با این ID قبلاً وجود دارد.");
            return false;
        }
        if (memberManager.SearchNationalCode(member.GetNationalCode())) {
            System.out.println("خطا: کد ملی قبلاً ثبت شده است.");
            return false;
        }
        memberManager.AddMember(member);
        System.out.println("کاربر با موفقیت اضافه شد. ID: " + member.GetID());
        return true;
    }

    public boolean deleteMember(int memberId) throws IOException {
        // بررسی وجود رزرو فعال برای کاربر
        Reserve reservations[] = reservationManager.GetReserve();
        for (int i = 0; i < reservationManager.GetRowCount(); i++) {
            if (reservations[i] != null && reservations[i].Getuser().GetID() == memberId &&
                    reservations[i].Getstatus().equals("CONFIRMED")) {
                System.out.println("خطا: کاربر دارای رزرو فعال است و نمی‌توان حذف کرد.");
                return false;
            }
        }
        Members member = new Members();
        member.SetID(memberId);
        if (memberManager.DeleteMember(member)) {
            System.out.println("کاربر با موفقیت حذف شد.");
            return true;
        } else {
            System.out.println("کاربر یافت نشد.");
            return false;
        }
    }

    public boolean updateMember(int memberId, Members updatedMember) throws IOException {
        String existingMember = memberManager.SearchMember(memberId);
        if (existingMember == null) {
            System.out.println("خطا: کاربر یافت نشد.");
            return false;
        }
        if (!updatedMember.GetNationalCode().equals(existingMember.split(Commons.Commons)[2]) &&
                memberManager.SearchNationalCode(updatedMember.GetNationalCode())) {
            System.out.println("خطا: کد ملی قبلاً ثبت شده است.");
            return false;
        }
        Members originalMember = new Members();
        originalMember.SetID(memberId);
        memberManager.UpdateMember(originalMember, updatedMember);
        System.out.println("کاربر با موفقیت به‌روزرسانی شد.");
        return true;
    }

    public String searchMember(int memberId) throws IOException {
        String result = memberManager.SearchMember(memberId);
        if (result == null) {
            return "کاربر یافت نشد.";
        }
        return result;
    }

    // --------------------------------------------------------> Room actions

    public boolean addRoom(Rooms room) throws IOException {
        String roomData = roomManager.search(room.GetNo());
        if (!roomData.equals("Invalid")) {
            System.out.println("خطا: اتاقی با این شماره قبلاً وجود دارد.");
            return false;
        }
        roomManager.AddRoom(room);
        System.out.println("اتاق با موفقیت اضافه شد. شماره: " + room.GetNo());
        return true;
    }

    public boolean deleteRoom(int roomNo) throws IOException {
        // بررسی وجود رزرو فعال برای اتاق
        Reserve reservations[] = reservationManager.GetReserve();
        for (int i = 0; i < reservationManager.GetRowCount(); i++) {
            if (reservations[i] != null && reservations[i].Getroom().GetNo() == roomNo &&
                    reservations[i].Getstatus().equals("CONFIRMED")) {
                System.out.println("خطا: اتاق دارای رزرو فعال است و نمی‌توان حذف کرد.");
                return false;
            }
        }
        String roomData = roomManager.search(roomNo);
        if (roomData.equals("Invalid")) {
            System.out.println("اتاق یافت نشد.");
            return false;
        }
        roomManager.Delete(roomNo);
        System.out.println("اتاق با موفقیت حذف شد.");
        return true;
    }

    public boolean updateRoom(int roomNo, Rooms updatedRoom) throws IOException {
        String existingRoom = roomManager.search(roomNo);
        if (existingRoom.equals("Invalid")) {
            System.out.println("خطا: اتاق یافت نشد.");
            return false;
        }
        roomManager.Update(roomNo, updatedRoom);
        System.out.println("اتاق با موفقیت به‌روزرسانی شد.");
        return true;
    }

    public String searchRoom(int roomNo) throws IOException {
        String result = roomManager.search(roomNo);
        if (result.equals("Invalid")) {
            return "اتاق یافت نشد.";
        }
        return result;
    }

    // ------------------------------------------------------------> Reserve actions

    public boolean reserveRoom(int memberId, int roomNo, LocalDate checkIn, LocalDate checkOut) throws IOException {
        // اعتبارسنجی تاریخ‌ها
        LocalDate today = LocalDate.now();
        if (checkIn.isBefore(today)) {
//            System.out.println("خطا: تاریخ شروع رزرو نمی‌تواند قبل از امروز باشد.");
            return false;
        }
        if (checkOut.isBefore(today) || checkOut.isEqual(today)) {
//            System.out.println("خطا: تاریخ پایان رزرو باید بعد از امروز باشد.");
            return false;
        }
        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
//            System.out.println("خطا: تاریخ پایان رزرو باید بعد از تاریخ شروع باشد.");
            return false;
        }

        // اعتبارسنجی کاربر و اتاق
        String memberData = memberManager.SearchMember(memberId);
        String roomData = roomManager.search(roomNo);
        if (memberData == null || roomData.equals("Invalid")) {
            System.out.println("کاربر یا اتاق نامعتبر است.");
            return false;
        }

        // ایجاد اشیاء کاربر و اتاق
        Members member = new Members();
        member.SetID(memberId);
        Rooms room = new Rooms();
        room.setNO(roomNo);
        String[] roomParts = roomData.split(Commons.Commons);
        room.SetFloor(Integer.parseInt(roomParts[1]));
        room.SetRoomType(roomParts[2]);
        room.SetPrice(Double.parseDouble(roomParts[3]));
        room.SetIsBussy(Boolean.parseBoolean(roomParts[4]));

        // ایجاد رزرو
        int reservationId =(int) (Math.random()*10000);
        Reserve reservation = new Reserve(reservationId, room ,  member, checkIn, checkOut, "CONFIRMED");

        // افزودن رزرو
        if (reservationManager.AddRes(reservation)) {
            System.out.println("رزرو با موفقیت انجام شد. ID رزرو: " + reservationId + "، قیمت کل: " + reservation.GettotalPrice());
            return true;
        } else {
            System.out.println("اتاق در این بازه زمانی در دسترس نیست.");
            return false;
        }
    }

    public boolean cancelReservation(int reservationId) throws IOException {
        if (reservationManager.delete(reservationId)) {
            System.out.println("رزرو با موفقیت لغو شد.");
            return true;
        } else {
            System.out.println("رزرو یافت نشد.");
            return false;
        }
    }

    public void checkExpiredReservations() throws IOException {
        reservationManager.updateExpiredReservations();
        System.out.println("رزروهای منقضی‌شده بررسی و به‌روزرسانی شدند.");
    }

    // Getter برای تست
    public MemberManager getMemberManager() { return memberManager; }
    public RoomManager getRoomManager() { return roomManager; }
}