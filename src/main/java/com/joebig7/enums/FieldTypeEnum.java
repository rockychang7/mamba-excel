package com.joebig7.enums;

import org.apache.poi.ss.usermodel.CellType;

/**
 * @Author JoeBig7
 * @date 2021/7/19 17:42:20
 * @description  field  type
 */
public enum FieldTypeEnum {
    INTEGER(0, CellType.NUMERIC),
    LONG(1, CellType.NUMERIC),
    DOUBLE(2, CellType.NUMERIC),
    FLOAT(3, CellType.NUMERIC),
    BOOLEAN(4, CellType.BOOLEAN),
    STRING(5, CellType.STRING),
    FORMULA(6, CellType.FORMULA),
    BLANK(7, CellType.BLANK);

    int type;
    CellType cellType;

    FieldTypeEnum(int type, CellType cellType) {
        this.type = type;
        this.cellType = cellType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
}
