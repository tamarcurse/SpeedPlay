/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package speedplay;

import java.io.Serializable;

/**
 *
 * @author Tichnut
 */
public class InfoFromServer implements Serializable{
    private Object info;
    private ETypeInfo typeInfo;

    public InfoFromServer() {
    }

    public InfoFromServer(Object info, ETypeInfo typeInfo) {
        this.info = info;
        this.typeInfo = typeInfo;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public ETypeInfo getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(ETypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    @Override
    public String toString() {
        return "InfoFromServer{" + "info=" + info + ", typeInfo=" + typeInfo + '}';
    }
    
}
