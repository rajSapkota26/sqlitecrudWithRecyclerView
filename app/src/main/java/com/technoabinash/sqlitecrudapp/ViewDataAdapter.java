package com.technoabinash.sqlitecrudapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewDataAdapter extends RecyclerView.Adapter<ViewDataAdapter.ViewHolder> {
    ArrayList<User> users;
    Context context;

    public ViewDataAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user=users.get(position);

        holder.name.setText("Name : "+user.getName());
        holder.mobile.setText("Email : "+user.getMobile());
        holder.address.setText("Address : "+user.getAddress());
        holder.email.setText("Email : "+user.getEmail());

        holder.edit.setOnClickListener(view -> {
            Intent intent=new Intent(context,AddDataActivity.class);
            intent.putExtra("edit",26);
            intent.putExtra("uId",user.getId());
            intent.putExtra("uName",user.getName());
            intent.putExtra("uEmail",user.getEmail());
            intent.putExtra("uMobile",user.getMobile());
            intent.putExtra("uAddress",user.getAddress());
            context.startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(view -> {
            SqliteHelper db=new SqliteHelper(context);
            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.alert_icon).setTitle("Delete Contact")
                    .setMessage("are you sure, you want to delete this!")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //write code here if user click on yes button on alert dialog
                            db.deletUserByUserId(user.getId());
                            Toast toast = Toast.makeText(context, "data deleted", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            context.startActivity(new Intent(context,ViewDataActivity.class));
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //write code here if use click on no button on alert dialog

                }
            }).show();
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,mobile,address;
        Button edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
            email=itemView.findViewById(R.id.tv_email);
            mobile=itemView.findViewById(R.id.tv_mobile);
            address=itemView.findViewById(R.id.tv_address);
            edit=itemView.findViewById(R.id.edt_Btn);


        }
    }
}
