package space.me.quiz;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0; //счётчик

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //создаём переменную text_levels

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

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

        //вызов диалогового окна в начале игры
        dialog = new Dialog(this); //создаём новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); //путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный цвет окна
        dialog.setCancelable(false);//диалог нельзя закрыть кнопкой назад

        //закрытие окна
        TextView buttonclose = (TextView) dialog.findViewById(R.id.buttonclose);
        buttonclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);// создали намерение для перехода
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

        //__________________________________________________
        //вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //создаём новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend); //путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный цвет окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);//диалог нельзя закрыть кнопкой назад

        //закрытие окна
        TextView buttonclose2 = (TextView) dialogEnd.findViewById(R.id.buttonclose);
        buttonclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);// создали намерение для перехода
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
            try{
                Intent intent = new Intent(Level1.this, Level2.class);
                startActivity(intent);finish();
            }catch (Exception e){

            }
                dialogEnd.dismiss();
            }
        });

        //__________________________________________________

        //кнопка назад
        Button buttonback = (Button) findViewById(R.id.button_back);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
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
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);


        numLeft = random.nextInt(10); //Генерируем случайное число от 0 до 9
        img_left.setImageResource(array.images1[numLeft]); //достаём из массива картинку
        text_left.setText(array.text1[numLeft]);
        numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9
        while (numLeft == numRight) {
            numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9
        }

        img_right.setImageResource(array.images1[numRight]); //достаём из массива картинку
        text_right.setText(array.text1[numRight]);//достаём из массива текст

        //обработка нажатия на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
                        numLeft = random.nextInt(10); //Генерируем случайное число от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]); //достаём из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.text1[numLeft]);
                        numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9
                        }

                        img_right.setImageResource(array.images1[numRight]); //достаём из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.text1[numRight]);//достаём из массива текст

                        img_right.setEnabled(true);//включаем правую картинку
                    }
                }

                return true;
            }
        });

        //обработка нажатия на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки
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
                        numLeft = random.nextInt(10); //Генерируем случайное число от 0 до 9
                        img_left.setImageResource(array.images1[numLeft]); //достаём из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.text1[numLeft]);
                        numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10); //Генерируем случайное число от 0 до 9
                        }

                        img_right.setImageResource(array.images1[numRight]); //достаём из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.text1[numRight]);//достаём из массива текст

                        img_left.setEnabled(true);//включаем левую картинку
                    }
                }

                return true;
            }
        });
    }

    //Изменение системной кнопки назад
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
}
