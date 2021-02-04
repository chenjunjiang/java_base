package com.chenjj.base.effective.clone;

import java.io.Serializable;

public class Major implements Cloneable, Serializable {
    private static final long serialVersionUID = 9097749858441790420L;

    private String majorName;
    private long majorId;

    public Major(String majorName, long majorId) {
        this.majorName = majorName;
        this.majorId = majorId;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Major{");
        sb.append("majorName='").append(majorName).append('\'');
        sb.append(", majorId=").append(majorId);
        sb.append('}');
        return sb.toString();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public long getMajorId() {
        return majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }
}
