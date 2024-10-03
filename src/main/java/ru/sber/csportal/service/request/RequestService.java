package ru.sber.csportal.service.request;

public interface RequestService<M,R> {
    R create(M model);
}
