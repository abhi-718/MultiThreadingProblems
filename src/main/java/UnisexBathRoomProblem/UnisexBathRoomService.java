package UnisexBathRoomProblem;

public class UnisexBathRoomService {

    private StringBuffer useBy = new StringBuffer();
    private static final UnisexBathRoomLockService unisexBathRoomLockService = new UnisexBathRoomLockService();
    public  void useMaleBathRoom() throws InterruptedException {
        unisexBathRoomLockService.acquireLockForMen();
//        if (useBy.length() == 0) {
//            useBy.append("MALE");
//        }
        System.out.println("This is currently Used by "+unisexBathRoomLockService.isUseBy +" and there count is "+unisexBathRoomLockService.countOfMen);
        Thread.sleep(1000);
        unisexBathRoomLockService.releaseLockForMen();
        useBy = new StringBuffer();
    }

    public  void useFemaleBathRoom() throws InterruptedException {
        unisexBathRoomLockService.acquireLockForWomen();
//        if (useBy.length() == 0) {
//            useBy.append("FEMALE");
//        }
        System.out.println("This is currently Used by "+unisexBathRoomLockService.isUseBy +" and there count is "+unisexBathRoomLockService.countOfWomen);
        Thread.sleep(2000);
        unisexBathRoomLockService.releaseLockForWomen();
        useBy = new StringBuffer();

    }

    public static void main(String[] args) throws InterruptedException {

        UnisexBathRoomService unisexBathRoomService = new UnisexBathRoomService();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    unisexBathRoomService.useFemaleBathRoom();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Women Thread "+ i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    unisexBathRoomService.useMaleBathRoom();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Men Thread "+ i).start();
        }
        Thread.sleep(10000);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    unisexBathRoomService.useFemaleBathRoom();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Women Thread "+ i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    unisexBathRoomService.useMaleBathRoom();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Men Thread "+ i).start();
        }
    }


}
