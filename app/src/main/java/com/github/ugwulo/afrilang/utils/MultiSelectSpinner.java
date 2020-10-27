package com.github.ugwulo.afrilang.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MultiSelectSpinner extends androidx.appcompat.widget.AppCompatSpinner implements
        DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnCancelListener {

    private List<String> items;
    private boolean[] selected;
    private String defaultText;
    private MultiSpinnerListener listener;

    public MultiSelectSpinner(Context context) {
        super(context);
    }

    public MultiSelectSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSelectSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        // refresh text on spinner
        StringBuffer spinnerBuffer = new StringBuffer();
        boolean someUnselected = false;
        for (int i = 0; i < items.size(); i++) {
            if (selected[i] == true) {
                spinnerBuffer.append(items.get(i));
                spinnerBuffer.append(",");
            } else {
                someUnselected = true;
            }
        }
        String spinnerText;
        if (someUnselected) {
            spinnerText = spinnerBuffer.toString();
            if (spinnerText.length() > 2)
                spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
        } else {
            spinnerText = defaultText;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[] { spinnerText });
        setAdapter(adapter);
        listener.onItemsSelected(selected);

    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (isChecked)
            selected[which] = true;
        else
            selected[which] = false;

    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(
                items.toArray(new CharSequence[items.size()]), selected, this);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.setOnCancelListener(this);
        builder.show();
        return true;
    }

    public void setItems(List<String> items, String allText,
                         MultiSpinnerListener listener) {
        this.items = items;
        this.defaultText = allText;
        this.listener = listener;

        // all selected by default
        selected = new boolean[items.size()];
        for (int i = 0; i < selected.length; i++)
            selected[i] = false;

        // all text on the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new String[] { allText });
        setAdapter(adapter);
    }

    public void setSelection(List<String> selection) {
        // uncheck any previous selection
        for (int i = 0; i < this.selected.length; i++) {
            this.selected[i] = false;
        }
        // check passed selection as true
        for (String sel : selection) {
            for (int j = 0; j < items.size(); ++j) {
                if (items.get(j).equals(sel)) {
                    this.selected[j] = true;
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new String[] { buildSelectedItemString()});
        setAdapter(adapter);

    }

    public ArrayList<String> getSelectedItemsArray() {
        ArrayList<String> selectedItems = new ArrayList<>();

        for (int i = 0; i < items.size(); ++i) {
            if (selected[i]) {
                selectedItems.add(items.get(i));
            }
        }

        return selectedItems;
    }

    public String getSelectedItemsString(){
        return buildSelectedItemString();
    }

    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < items.size(); ++i) {
            if (selected[i]) {
                if (foundOne) {
                    sb.append(",");
                }

                foundOne = true;

                sb.append(items.get(i));
            }
        }

        return sb.toString();
    }

    public interface MultiSpinnerListener {
        public void onItemsSelected(boolean[] selected);
    }
}
