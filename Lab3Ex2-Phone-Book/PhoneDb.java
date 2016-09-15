package com.klcal.lab3ex2;

public class PhoneDb {

    private int    _id;
    private String _name;
    private String    _mobile;
    private String _office;
    private String _email;

    public PhoneDb() {
        this._id = 0;
        this._name = "";
        this._mobile = "";
        this._email = "";
    }

    public PhoneDb(int _id, String _name, String _mobile, String _office, String _email) {
        this._id = _id;
        this._name = _name;
        this._mobile = _mobile;
        this._office = _office;
        this._email = _email;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_mobile() {
        return _mobile;
    }

    public String get_office() {
        return _office;
    }

    public String get_email() {
        return _email;
    }
}
