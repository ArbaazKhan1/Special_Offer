package com.example.hw3_special_offer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.squareup.picasso.Picasso;

public class SpecialOfferActivity extends AppCompatActivity {
    private static final String TAG = "SpecialOfferActivity";
    private ImageView logo;
    private ImageView qrCode;
    private TextView name;
    private TextView address;
    private TextView website;
    private TextView offer;
    private ConstraintLayout layout;
    private Typeface textFont;
    private Fence fence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textFont = Typeface.createFromAsset(getAssets(), "fonts/Acme-Regular.ttf");
        setContentView(R.layout.activity_special_offer);
        logo = findViewById(R.id.businessLogo_imageView);
        qrCode = findViewById(R.id.QRCode_imageView);
        name = findViewById(R.id.businessName_textView);
        address = findViewById(R.id.address_textView);
        website = findViewById(R.id.website_textView);
        offer = findViewById(R.id.offerDetails_textView);
        layout = findViewById(R.id.layout);

        loadIntent();
        makeQR();

    }

    private void loadIntent(){
        Intent intent = getIntent();
        fence = (Fence) intent.getSerializableExtra("fenceData");
        name.setText(fence.getId());
        name.setTypeface(textFont);

        address.setText(fence.getAddress());
        address.setTypeface(textFont);
        Linkify.addLinks(address,Linkify.ALL);

        website.setText(fence.getWebsite());
        website.setTypeface(textFont);
        Linkify.addLinks(website,Linkify.ALL);

        offer.setText(fence.getMessage());
        offer.setTypeface(textFont);

        Picasso picasso = new Picasso.Builder(this).build();
        picasso.load(fence.getLogo())
                .error(R.drawable.brokenimage)
                .placeholder(R.drawable.placeholder)
                .into(logo);

        layout.setBackgroundColor(Color.parseColor(fence.getColor()));
    }

    private void makeQR() {
        QRCodeWriter writer = new QRCodeWriter();
        try { BitMatrix bitMatrix = writer.encode(fence.getCode(), BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            // The below Bitmap is what will be displayed in an ImageView in the Activity
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            // The below line uses the Bitmap just created as the ImageView’s image bitmap((ImageView) findViewById(R.id.myImageView)).setImageBitmap(bmp);
            qrCode.setImageBitmap(bmp);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
