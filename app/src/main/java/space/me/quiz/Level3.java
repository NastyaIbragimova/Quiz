package space.me.quiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level3 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0; //счётчик

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //создаём переменную text_levels

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level3);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);

        //Скруглить углы
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);
        //путь к картинкам
        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        //Развернуть игру на весь экран
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Устанавливаем фон
        ImageView background = (ImageView) findViewById(R.id.level1);
        background.setImageResource(R.drawable.level3);

        //вызов диалогового окна в начале игры
        dialog = new Dialog(this); //создаём новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); //путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный цвет окна
        dialog.setCancelable(false);//диалог нельзя закрыть кнопкой назад

        //Устанавливаем картинку в диалоговое окно
        ImageView previewImg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewImg.setImageResource(R.drawable.previewimg3);

        //Устанавливаем фон диалогового окна
        LinearLayout dialogFon = (LinearLayout) dialog.findViewById(R.id.dialogfon);
        dialogFon.setBackgroundResource(R.drawable.previewbackground3);

        //Устанавливаем текст в диалоговое окно
        TextView textDescription = (TextView) dialog.findViewById(R.id.textdexcription);
        textDescription.setText(R.string.levelthree);


        //закрытие окна
        TextView buttonclose = (TextView) dialog.findViewById(R.id.buttonclose);
        buttonclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);// создали намерение для перехода
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialog.dismiss();//закрываем диалоговое окно
            }
        });
        //кнопка продолжить
        Button buttoncontinue = (Button) dialog.findViewById(R.id.buttoncontinue);
        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();//показать окно

        //вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //создаём новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend); //путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный цвет окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);//диалог нельзя закрыть кнопкой назад

        //фон
        LinearLayout dialogFonEnd = (LinearLayout) dialogEnd.findViewById(R.id.dialogfon);
        dialogFonEnd.setBackgroundResource(R.drawable.previewbackground3);

        //интересный факт
        TextView textDescriptionEnd = (TextView) dialogEnd.findViewById(R.id.textdexcriptionend);
        textDescriptionEnd.setText(R.string.levelthreeend);


        //закрытие окна
        TextView buttonclose2 = (TextView) dialogEnd.findViewById(R.id.buttonclose);
        buttonclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);// создали намерение для перехода
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss();//закрываем диалоговое окно
            }
        });
        //кнопка продолжить
        Button buttoncontinue2 = (Button) dialogEnd.findViewById(R.id.buttoncontinue);
        buttoncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, Level4.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss();
            }
        });


        //кнопка назад
        Button buttonback = (Button) findViewById(R.id.button_back);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
//массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4,
                R.id.point5, R.id.point6, R.id.point7, R.id.point8,
                R.id.point9, R.id.point10, R.id.point11, R.id.point12,
                R.id.point13, R.id.point14, R.id.point15, R.id.point16,
                R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };


        //подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(Level3.this, R.anim.alpha);


        numLeft = random.nextInt(21); //Генерируем случайное число
        img_left.setImageResource(array.images3[numLeft]); //достаём из массива картинку
        text_left.setText(array.text3[numLeft]);
        numRight = random.nextInt(21); //Генерируем случайное число
        while (numLeft == numRight) {
            numRight = random.nextInt(21); //Генерируем случайное число
        }
        img_right.setImageResource(array.images3[numRight]); //достаём из массива картинку
        text_right.setText(array.text3[numRight]);//достаём из массива текст

        //обработка нажатия на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {

                    //условие касания картинки
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //если коснулся картинки
                        img_right.setEnabled(false);//блокируем правую картинку
                        if (numLeft > numRight) {
                            img_left.setImageResource(R.drawable.img_true);
                        } else img_left.setImageResource(R.drawable.img_false);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        //если отпустил картинку
                        if (numLeft > numRight) {
                            if (count < 20) {
                                count++;
                            }
                            //закрашиваем прогресс
                            for (int i = 0; i < 20; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //закрашиваем прогресс по правильным ответам
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                        } else {
                            //если левая картинка меньше
                            if (count > 0) {
                                if (count == 1) {
                                    count = 0;
                                } else {
                                    count = count - 2;
                                }
                            }
                            //закрашиваем прогресс
                            for (int i = 0; i < 19; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //закрашиваем прогресс по правильным ответам
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                        }
                        if (count == 20) {
                            //ВЫХОД ИЗ УРОВНЯ
                              dialogEnd.show();

                        } else {
                            numLeft = random.nextInt(21); //Генерируем случайное число
                            img_left.setImageResource(array.images3[numLeft]); //достаём из массива картинку
                            img_left.startAnimation(a);
                            text_left.setText(array.text3[numLeft]);
                            numRight = random.nextInt(21); //Генерируем случайное число
                            while (numLeft == numRight) {
                                numRight = random.nextInt(21); //Генерируем случайное число
                            }
                            img_right.setImageResource(array.images3[numRight]); //достаём из массива картинку
                            img_right.startAnimation(a);
                            text_right.setText(array.text3[numRight]);//достаём из массива текст

                            img_right.setEnabled(true);//включаем правую картинку
                        }
                    }
                }catch (Exception e){

                }

                return true;
            }
        });

        //обработка нажатия на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки
                try {

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //если коснулся картинки
                        img_left.setEnabled(false);//блокируем левую картинку
                        if (numRight > numLeft) {
                            img_right.setImageResource(R.drawable.img_true);
                        } else img_right.setImageResource(R.drawable.img_false);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        //если отпустил картинку
                        if (numRight > numLeft) {
                            if (count < 20) {
                                count++;
                            }
                            //закрашиваем прогресс
                            for (int i = 0; i < 20; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //закрашиваем прогресс по правильным ответам
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                        } else {
                            //если правая картинка меньше
                            if (count > 0) {
                                if (count == 1) {
                                    count = 0;
                                } else {
                                    count = count - 2;
                                }
                            }
                            //закрашиваем прогресс
                            for (int i = 0; i < 19; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //закрашиваем прогресс по правильным ответам
                            for (int i = 0; i < count; i++) {
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                        }
                        if (count == 20) {
                            //ВЫХОД ИЗ УРОВНЯ
                             dialogEnd.show();

                        } else {
                            numLeft = random.nextInt(21); //Генерируем случайное число
                            img_left.setImageResource(array.images3[numLeft]); //достаём из массива картинку
                            img_left.startAnimation(a);
                            text_left.setText(array.text3[numLeft]);
                            numRight = random.nextInt(21); //Генерируем случайное число
                            while (numLeft == numRight) {
                                numRight = random.nextInt(21); //Генерируем случайное число
                            }
                            img_right.setImageResource(array.images3[numRight]); //достаём из массива картинку
                            img_right.startAnimation(a);
                            text_right.setText(array.text3[numRight]);//достаём из массива текст

                            img_left.setEnabled(true);//включаем левую картинку
                        }
                    }
                }catch (Exception e){

                }
                    return true;

            }
        });
    }

    //Изменение системной кнопки назад
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level3.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
}
