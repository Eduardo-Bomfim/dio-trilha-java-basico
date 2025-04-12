package org.example.ui.custom.input;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.List;

import static java.util.Objects.isNull;

public class NumberTextLimit extends PlainDocument {

    private final List<String> NUMBERS = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

    @Override
    public void insertString(final int offset, final String str, final AttributeSet attr) throws BadLocationException {
        if (isNull(str) || (!NUMBERS.contains(str) && !str.equals(" "))) return;

        if (getLength() + str.length() <= 1) super.insertString(offset, str, attr);
    }
}
