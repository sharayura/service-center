package entity;

public enum Status {
    NEW("Новый"),
    DIAGNOSTIC("Диагностика"),
    WAITING_AGREEMENT("На согласовании"),
    WAITING_PARTS("Ожидает запчасти"),
    IN_WORK("В работе"),
    WAITING_PAYMENT("Готов, ожидает оплаты"),
    PAYED("Готов, оплачен"),
    CLOSE("Выдан");

    public final String transcription;

    Status(String transcription) {
        this.transcription = transcription;
    }
}
