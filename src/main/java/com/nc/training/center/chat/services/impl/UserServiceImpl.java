package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private String name;
    private String info;
    private boolean connected = false;
    private static Map<Long , User> userRepo = new HashMap<>();
    private static List<String> messageRepo = new ArrayList<>();

    @Override
    public void retrieveMessage(String message) throws RemoteException {
    }

    @Override
    public void connect(User user) throws RemoteException {
        if(connected==false) {
            userRepo.put(user.getId(), user);
            connected=true;
        }
    }

    @Override
    public void disconnect(User user) throws RemoteException {
        if(connected == true) {
            connected=false;
            userRepo.remove(user.getId(), user);
        }
    }

    @Override
    public void send() throws RemoteException {

    }

    @Override
    public String getName() throws RemoteException {
        return null;
    }

    @Override
    public String getInfo() throws RemoteException {
        return null;
    }
}
