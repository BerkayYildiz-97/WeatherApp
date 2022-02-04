package com.etiya.weatherApp.core.utilities;

public class ExecutionTime {
    private long start;
    private long end;

    public ExecutionTime(boolean start) {
        this.start = 0;
        this.end = 0;
        if (start) {
            this.startTask();
        } else {
            this.reset();
        }

    }

    public void startTask() {
        this.start = System.currentTimeMillis();
        this.end = this.start;
    }

    public void endTask() {
        this.end = System.currentTimeMillis();
    }

    public long duration() {
        return this.end - this.start;
    }

    public void reset() {
        this.start = 0L;
        this.end = 0L;
    }

}
