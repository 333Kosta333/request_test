package ru.sber.csportal.service.request;

public interface RequestCreator <R, T>{
    R create(T request);
}
