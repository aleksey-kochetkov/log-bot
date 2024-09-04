package ru.ceki.fgiski2.logbot.dto;

public class ShutdownElement implements QueueElement {
    private Runnable runnable;

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public void onProcessed() {
        if (this.runnable != null) {
            this.runnable.run();
        }
    }
}
