package pl.project.oop.java;

public class Skill {
    private boolean isActive;
    private int duration;
    private int cooldown;
    private boolean isReadyToActivate;

    public Skill() {
        cooldown = 0;
        duration = 0;
        isActive = false;
        isReadyToActivate = true;
    }

    public void checkConditions() {
        if (cooldown > 0) cooldown--;
        if (duration > 0) duration--;
        if (duration == 0) deactivate();
        if (cooldown == 0) isReadyToActivate = true;
    }

    public void activate() {
        if (cooldown == 0) {
            isActive = true;
            isReadyToActivate = false;
            int COOLDOWN = 10;
            cooldown = COOLDOWN;
            int DURATION = 5;
            duration = DURATION;
        }
    }

    public void deactivate() {
        isActive = false;
    }

    public boolean getIsReadyToActivate() {
        return isReadyToActivate;
    }

    public void setIsReadyToActivate(boolean isReadyToActivate) {
        this.isReadyToActivate = isReadyToActivate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}
