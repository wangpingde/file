package com.hoperun.qkl.fileserver.constant;


/**
 * 文件的权限
 */
public enum  FilePermissions {

    all("all","2"),

    readOnly("readOnly","2"),

    upLoad("upLoad","2"),

    downLoad("downLoad","2"),

    delete("delete","2");

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private FilePermissions(String code, String desc) {

        this.code = code;

        this.desc = desc;

    }

    public static FilePermissions fromCode(String code) {

        FilePermissions[] codes = FilePermissions.values();

        for(FilePermissions fp : codes){
           if( fp.getCode().equalsIgnoreCase(code)){
               return fp;
           }
        }

        return null;

    }


}
