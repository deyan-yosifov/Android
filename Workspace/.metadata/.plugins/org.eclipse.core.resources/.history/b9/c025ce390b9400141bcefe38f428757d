package com.dipiuay.fallingrocks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class MessageBoxHelper {
	public static final DialogInterface.OnClickListener EmptyClickListener;
	private static final String DefaultAlertHeader;
	
	static {
		EmptyClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // do nothing
            }
         };
         
         DefaultAlertHeader = "Alert!";
	}
	
	public static void Alert(Context context, String content){
		MessageBoxHelper.Alert(context, content, DefaultAlertHeader);		
	}
	
	public static void Alert(Context context, String content, String header){
		MessageBoxHelper.Alert(context, content, header, EmptyClickListener, EmptyClickListener);		
	}
	
	public static void Alert(Context context, String content, String header, DialogInterface.OnClickListener yesClickListener){
		MessageBoxHelper.Alert(context, content, header, yesClickListener, EmptyClickListener);		
	}
	
	public static void Alert(Context context, String content, String header,
			DialogInterface.OnClickListener yesClickListener, DialogInterface.OnClickListener noClickListener){
		new AlertDialog.Builder(context)
        .setTitle(header)
        .setMessage(content)
        .setPositiveButton(android.R.string.yes, yesClickListener)
        .setNegativeButton(android.R.string.no, noClickListener)
        .setIcon(android.R.drawable.ic_dialog_alert)
         .show();    	
	}
}
