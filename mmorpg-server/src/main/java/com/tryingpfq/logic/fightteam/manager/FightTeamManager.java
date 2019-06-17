package com.tryingpfq.logic.fightteam.manager;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author Tryingpfq
 * @Time 2019/5/26 22:41
 */
@Component
public class FightTeamManager {

    private Set<String> registerName = Sets.newConcurrentHashSet();

    public boolean registerName(String name) {
        return registerName.add(name);
    }
}
