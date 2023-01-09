package observer;

public interface MyObserver {
    /**
     * Updates an observer based on a notification
     * @param o observable object that generates the notification
     * @param arg notification
     */
    void update(MyObservable o, Object arg);
}
