package com.example.petclinic;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }
    AwesomeValidation awesomeValidation;

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull MainModel model) {

        holder.name.setText(model.getName());
        holder.date.setText(model.getDate());

        Glide.with(holder.img.getContext())
                .load(model.getPurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);



         holder.btnEdit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                         .setContentHolder(new ViewHolder(R.layout.update_popup))
                         .setExpanded(true,1800)
                         .create();

                 //dialogPlus.show();
                 View view = dialogPlus.getHolderView();

                 EditText name = view.findViewById(R.id.txtName);
                 EditText type = view.findViewById(R.id.txtType);
                 EditText purl = view.findViewById(R.id.txtImageUrl);
                 EditText owner = view.findViewById(R.id.txtOwner);
                 EditText age = view.findViewById(R.id.txtAge);
                 EditText phone = view.findViewById(R.id.txtPhone);
                 EditText email = view.findViewById(R.id.txtEmail);
                 EditText city = view.findViewById(R.id.txtCity);
                 EditText date = view.findViewById(R.id.txtDate);

                 Button btnUpdate = view.findViewById(R.id.btnUpdate);

                 name.setText(model.getName());
                 type.setText(model.getType());
                 purl.setText(model.getPurl());
                 owner.setText(model.getOwner());
                 age.setText(model.getAge());
                 phone.setText(model.getPhone());
                 email.setText(model.getEmail());
                 city.setText(model.getCity());
                 date.setText(model.getDate());

                 dialogPlus.show();

                 btnUpdate.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         Map<String,Object> map = new HashMap<>();
                         map.put("name",name.getText().toString());
                         map.put("type",type.getText().toString());
                         map.put("purl",purl.getText().toString());
                         map.put("owner",owner.getText().toString());
                         map.put("age",age.getText().toString());
                         map.put("phone",phone.getText().toString());
                         map.put("email",email.getText().toString());
                         map.put("city",city.getText().toString());
                         map.put("date",date.getText().toString());

                         FirebaseDatabase.getInstance().getReference().child("Pets")
                                 .child(getRef(position).getKey()).updateChildren(map)
                                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void unused) {
                                         Toast.makeText(holder.name.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                         dialogPlus.dismiss();
                                     }
                                 })
                                 .addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) {
                                         Toast.makeText(holder.name.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                         dialogPlus.dismiss();

                                     }
                                 });






                     }
                 });




             }
         });


         holder.btnDelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                 builder.setTitle("Are You Sure ?");
                 builder.setMessage("Deleted data can't be Undo.");

                 builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         FirebaseDatabase.getInstance().getReference().child("Pets")
                                 .child(getRef(position).getKey()).removeValue();
                     }
                 });
                 builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                     }
                 });
                 builder.show();



             }
         });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name, date;

        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            date = (TextView) itemView.findViewById(R.id.datetext);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }
}