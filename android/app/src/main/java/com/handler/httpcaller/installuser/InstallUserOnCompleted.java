package com.handler.httpcaller.installuser;

import com.handler._entity.InUseFriendEntity;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-07-20.
 */
public interface InstallUserOnCompleted {
    void installUserOnCompleted(ArrayList<InUseFriendEntity> friendList);
}
