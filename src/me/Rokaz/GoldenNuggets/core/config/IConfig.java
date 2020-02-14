package me.Rokaz.GoldenNuggets.core.config;

public abstract interface IConfig {
    public abstract String getConfig();
    public abstract void setupKeys();
    public abstract void save();
}
