package com.example.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {
    static CharSequence[] items = {"Google", "Apple", "Microsoft"};
    boolean[] itemsChecked = new boolean[items.length];
    ProgressDialog progressDialog;

    static AlertDialogFragment newInstance(String title, int id){
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        int id = getArguments().getInt("id");
        switch (id) {
            case 0:
                return new AlertDialog.Builder(getActivity())
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(title)
                        .setMultiChoiceItems(items, itemsChecked,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        ((MainActivity) getActivity()).doSelectedItem(which, isChecked);
                                    }
                                })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((MainActivity) getActivity()).actionClick("Ok");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((MainActivity) getActivity()).actionClick("Cancel");
                            }
                        }).create();
            case 1:
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.setTitle("Descargando");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((MainActivity) getActivity()).actionClick("Ok");
                            }
                        });
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((MainActivity) getActivity()).actionClick("Cancel");
                            }
                        });
                progressDialog.setProgress(0);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=1; i <= 15; i++){
                            try {
                                Thread.sleep(1000);
                                progressDialog.incrementProgressBy((int) (100 / 15));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        progressDialog.dismiss();
                    }
                }).start();
                return progressDialog;
        }
        return null;
    }

}
