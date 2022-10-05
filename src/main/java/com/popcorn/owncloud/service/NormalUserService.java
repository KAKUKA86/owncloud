package com.popcorn.owncloud.service;

import com.popcorn.owncloud.bean.NormalUser;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface NormalUserService {
    List<NormalUser> queryNormalUsersByUserName(String username);
}
