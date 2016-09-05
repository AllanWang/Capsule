//package com.pitchedapps.capsule.library.cards;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import pitchedapps.neuron.R;
//import pitchedapps.neuron.frames.ButtonFrame;
//import pitchedapps.neuron.frames.CardFrame;
//import pitchedapps.neuron.utils.WebUtils;
//import pitchedapps.neuron.views.ButtonRow;
//import timber.log.Timber;
//
//public class CapsuleCardHolder extends RecyclerView.ViewHolder {
//
//    private Context mContext;
//    private CapsuleCardFrame mCardFrame;
//    private View v;
//    private static final String PLAY_STORE = "https://play.google.com/store/apps/details?id=";
//
//    public CapsuleCardHolder(Context c, View itemView, CardFrame cf) {
//        super(itemView);
//        mContext = c;
//        v = itemView;
//        mCardFrame = cf;
//        setupContent();
//    }
//
//    private void setupContent() {
//        switch (mCardFrame.getCardType()) {
//            case HomeHeader:
//                setTextViewText(R.id.home_header_title, mCardFrame.getTitle());
//                setTextViewText(R.id.home_header_desc, mCardFrame.getDesc());
//                if (!mCardFrame.getButtons().isEmpty()) {
//                    ButtonRow buttonRow = (ButtonRow) v.findViewById(R.id.home_buttons);
//                    for (ButtonFrame b : mCardFrame.getButtons()) {
//                        buttonRow.addButton(b.toNeuronButton(mContext));
//                    }
//                    buttonRow.initialize();
//                }
//                break;
//            case Link:
//                setTextViewText(R.id.card_link_title, mCardFrame.getTitle());
//                setTextViewText(R.id.card_link_description, mCardFrame.getDesc());
//                if (mCardFrame.hasImage()) {
//                    setupImageView(R.id.card_link_image, mCardFrame.getDrawable());
//                    v.findViewById(R.id.card_link_sub_space).setVisibility(View.VISIBLE);
//                }
//                if (mCardFrame.isLink()) {
//                    v.findViewById(R.id.card_frame).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            WebUtils.openLinkInChromeCustomTab(mContext, mCardFrame.getLink());
//                        }
//                    });
//                } else if (mCardFrame.isApp()) {
//                    v.findViewById(R.id.card_frame).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            WebUtils.openLinkInChromeCustomTab(mContext, PLAY_STORE + mCardFrame.getApp());
//                        }
//                    });
//                }
//
//                break;
//            case FAQ:
//                setTextViewText(R.id.faq_question, mCardFrame.getTitle());
//                setTextViewText(R.id.faq_answer, mCardFrame.getDesc());
//                break;
//            default:
//                Timber.e("Unknown CardType", mCardFrame.getCardType().toString());
//                break;
//        }
//    }
//
//    private TextView setTextViewText(int id, String s) {
//        TextView t = (TextView) v.findViewById(id);
//        t.setText(s);
//        return t;
//    }
//
//    private Button setupButton(int id, String s, View.OnClickListener c) {
//        Button b = (Button) v.findViewById(id);
//        b.setText(s);
//        b.setOnClickListener(c);
//        return b;
//    }
//
//    private ImageView setupImageView(int id, Drawable d) {
//        ImageView iv = (ImageView) v.findViewById(id);
//        iv.setImageDrawable(d);
//        return iv;
//    }
//
//    private String s(int id) {
//        return mContext.getResources().getString(id);
//    }
//
//
//}