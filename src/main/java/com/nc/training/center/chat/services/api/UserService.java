package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.User;

import java.rmi.RemoteException;

public interface UserService {

    void retrieveMessage(String message) throws RemoteException;//получать сообщения других пользователей

    void connect(User user) throws RemoteException;

    void disconnect(User user) throws RemoteException;

    void send() throws RemoteException;

    String getName() throws RemoteException;

    String getInfo() throws RemoteException;

    //ChatServerInterface getChatServer() throws RemoteException;
}
