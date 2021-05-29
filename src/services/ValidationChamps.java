/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.jfoenix.controls.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Neifos
 */
public class ValidationChamps {

    public static boolean isTextFieldNotEmpty(TextField tf) {
        boolean b = false;
        if (tf.getText().length() != 0 || !tf.getText().isEmpty()) {
            b = true;
        }
        return b;
    }
    public static boolean isTextFieldNotNumber(TextField tf, Label lb) {
        String msg = null;
        boolean b = true;
       if (!(tf.getText()+0).matches("^[0-9]+$")) {
            b = false;
            msg = "vous devez saisir un nombre";
        }
        lb.setText(msg);
        return b;
    }

    public static boolean isTextFieldNotEmptyNumber(TextField tf, Label lb) {
        String msg = null;
        boolean b = true;
        if (tf.getText().length() == 0 || tf.getText().isEmpty()) {
            b = false;
            msg = "ce champ ne doit pas  etre  vide";
        } else if (!tf.getText().matches("^[0-9]+$")) {
            b = false;
            msg = "vous devez saisir un nombre";
        }
        lb.setText(msg);
        return b;
    }

    public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage) {
        boolean b = true;
        String msg = null;
        if (tf.getText().length() == 0 || tf.getText().isEmpty()) {
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;

    }

    public static boolean isTextAreaNotEmpty(JFXTextArea tf, Label lb, String errorMessage) {
        boolean b = true;
        String msg = null;
        if (tf.getText().length() == 0 || tf.getText().isEmpty()) {
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;

    }

    public static boolean isComboBoxNotEmpty(ComboBox tf, Label lb, String errorMessage) {
        boolean b = true;
        String msg = null;
        if (tf.getValue() == null) {
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;

    }

    public static boolean isDatePickerNotEmpty(DatePicker tf, Label lb, String errorMessage) {
        boolean b = true;
        String msg = null;
        if (tf.getValue() == null) {
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;

    }

    public static boolean isMailField(TextField tf, Label lb, String errorMessage) {
        boolean b = true;
        String msg = null;
        if (!tf.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;

    }

    public static boolean isDateEqToDate(JFXDatePicker tp, JFXDatePicker tp2, Label lb, String errorMessage) {
        boolean b = true;
        String msg = null;
        if (tp.getValue().isAfter(tp2.getValue())) {
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;

    }

    public static boolean isTimeEqToTime(JFXTimePicker tp, JFXTimePicker tp2, Label lb, String errorMessage) {
        boolean b = true;
        String msg = null;
        if (tp.getValue().isAfter(tp2.getValue())) {
            b = false;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;

    }

}
