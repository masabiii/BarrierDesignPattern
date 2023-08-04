/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barrierdesignpatter;

/**
 *
 * @author mhady
 */
public class Member {
    private String name;
    private boolean isMember;

    public Member(String name, boolean isMember) {
        this.name = name;
        this.isMember = isMember;
    }

    public String getName() {
        return name;
    }

    public boolean isMember() {
        return isMember;
    }
}
