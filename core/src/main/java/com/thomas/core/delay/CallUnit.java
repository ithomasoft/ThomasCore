package com.thomas.core.delay;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class CallUnit {
    //目标行为

    private Action action;

    //先进先出验证模型

    private Queue<Valid> validQueue = new ArrayDeque<>();

    //上一个执行的valid

    private Valid lastValid;


    public CallUnit() {
    }

    public CallUnit(Action action) {
        this.action = action;
    }

    public static CallUnit newInstance(Action action) {
        return new CallUnit(action);
    }

    public Valid getLastValid() {
        return lastValid;
    }

    public void setLastValid(Valid lastValid) {
        this.lastValid = lastValid;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public CallUnit addValid(Valid valid) {
        validQueue.add(valid);
        return this;
    }

    public Queue<Valid> getValidQueue() {
        return validQueue;
    }

    //检查valid.如果已经满足要求，则移出来队列

    public void check() {
        for (Valid valid : validQueue) {
            if (valid.check()) {
                validQueue.remove(valid);
            }
        }
    }

    /**
     * start
     */
    public void doCall() {
        ActionManager.instance().postCallUnit(this);
    }

}
