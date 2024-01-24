package formation.android.memory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.lang.String;
import java.lang.Object;
import java.util.Collections;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends Activity {

    public int TurnNumber = 0;
    public int Points = 0;
    public int RevealedCards = 0;
    public int ReturnedCard[] = new int[2];
    public int ReturnedCardImage[] = new int[1];
    public int DiscoveredOK[] = new int[16];
    public int PairDiscovered = 0;

    public int[] ImageListe = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6,
            R.drawable.image7, R.drawable.image7, R.drawable.image8, R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6,
            R.drawable.image7, R.drawable.image7, R.drawable.image8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartGame();
        shuffleArray(ImageListe);

        final TextView nbrTour = findViewById(R.id.nbrTour);
        final TextView nbrPoints = findViewById(R.id.nbrPoints);
        
        final Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[0] != 1 && ReturnedCard[0] != R.id.button1 && ReturnedCard[1] != R.id.button1) {
                    //Afficher le fond
                    button1.setBackgroundResource(ImageListe[0]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[0];
                        ReturnedCard[0] = R.id.button1;
                        ReturnedCard[1] = 0;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[0]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[0] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button1;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 1 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[0];
                        ReturnedCard[0] = R.id.button1;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[1] != 1 && ReturnedCard[0] != R.id.button2 && ReturnedCard[1] != R.id.button2) {
                    //Afficher le fond
                    button2.setBackgroundResource(ImageListe[1]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[1];
                        ReturnedCard[0] = R.id.button2;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[1]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[1] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button2;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[1];
                        ReturnedCard[0] = R.id.button2;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[2] != 1 && ReturnedCard[0] != R.id.button3 && ReturnedCard[1] != R.id.button3) {
                    //Afficher le fond
                    button3.setBackgroundResource(ImageListe[2]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[2];
                        ReturnedCard[0] = R.id.button3;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[2]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[2] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button3;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[2];
                        ReturnedCard[0] = R.id.button3;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[3] != 1 && ReturnedCard[0] != R.id.button4 && ReturnedCard[1] != R.id.button4) {
                    //Afficher le fond
                    button4.setBackgroundResource(ImageListe[3]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[3];
                        ReturnedCard[0] = R.id.button4;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[3]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[3] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button4;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[3];
                        ReturnedCard[0] = R.id.button4;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[4] != 1 && ReturnedCard[0] != R.id.button5 && ReturnedCard[1] != R.id.button5) {
                    //Afficher le fond
                    button5.setBackgroundResource(ImageListe[4]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[4];
                        ReturnedCard[0] = R.id.button5;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[4]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[4] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button5;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[4];
                        ReturnedCard[0] = R.id.button5;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[5] != 1 && ReturnedCard[0] != R.id.button6 && ReturnedCard[1] != R.id.button6) {
                    //Afficher le fond
                    button6.setBackgroundResource(ImageListe[5]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[5];
                        ReturnedCard[0] = R.id.button6;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[5]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[5] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button6;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[5];
                        ReturnedCard[0] = R.id.button6;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[6] != 1 && ReturnedCard[0] != R.id.button7 && ReturnedCard[1] != R.id.button7) {
                    //Afficher le fond
                    button7.setBackgroundResource(ImageListe[6]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[6];
                        ReturnedCard[0] = R.id.button7;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[6]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[6] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button7;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[6];
                        ReturnedCard[0] = R.id.button7;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[7] != 1 && ReturnedCard[0] != R.id.button8 && ReturnedCard[1] != R.id.button8) {
                    //Afficher le fond
                    button8.setBackgroundResource(ImageListe[7]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[7];
                        ReturnedCard[0] = R.id.button8;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[7]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[7] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button8;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[7];
                        ReturnedCard[0] = R.id.button8;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[8] != 1 && ReturnedCard[0] != R.id.button9 && ReturnedCard[1] != R.id.button9) {
                    //Afficher le fond
                    button9.setBackgroundResource(ImageListe[8]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[8];
                        ReturnedCard[0] = R.id.button9;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[8]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[8] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button9;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[8];
                        ReturnedCard[0] = R.id.button9;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[9] != 1 && ReturnedCard[0] != R.id.button10 && ReturnedCard[1] != R.id.button10) {
                    //Afficher le fond
                    button10.setBackgroundResource(ImageListe[9]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[9];
                        ReturnedCard[0] = R.id.button10;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[9]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[9] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button10;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[9];
                        ReturnedCard[0] = R.id.button10;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[10] != 1 && ReturnedCard[0] != R.id.button11 && ReturnedCard[1] != R.id.button11) {
                    //Afficher le fond
                    button11.setBackgroundResource(ImageListe[10]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[10];
                        ReturnedCard[0] = R.id.button11;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[10]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[10] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button11;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[10];
                        ReturnedCard[0] = R.id.button11;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[11] != 1 && ReturnedCard[0] != R.id.button12 && ReturnedCard[1] != R.id.button12) {
                    //Afficher le fond
                    button12.setBackgroundResource(ImageListe[11]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[11];
                        ReturnedCard[0] = R.id.button12;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[11]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[11] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button12;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[11];
                        ReturnedCard[0] = R.id.button12;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button13 = findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[12] != 1 && ReturnedCard[0] != R.id.button13 && ReturnedCard[1] != R.id.button13) {
                    //Afficher le fond
                    button13.setBackgroundResource(ImageListe[12]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[12];
                        ReturnedCard[0] = R.id.button13;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[12]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[12] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button13;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[12];
                        ReturnedCard[0] = R.id.button13;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button14 = findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[13] != 1 && ReturnedCard[0] != R.id.button14 && ReturnedCard[1] != R.id.button14) {
                    //Afficher le fond
                    button14.setBackgroundResource(ImageListe[13]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[13];
                        ReturnedCard[0] = R.id.button14;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[13]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[13] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button14;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[13];
                        ReturnedCard[0] = R.id.button14;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button15 = findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[14] != 1 && ReturnedCard[0] != R.id.button15 && ReturnedCard[1] != R.id.button15) {
                    //Afficher le fond
                    button15.setBackgroundResource(ImageListe[14]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[14];
                        ReturnedCard[0] = R.id.button15;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[14]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[14] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button15;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[14];
                        ReturnedCard[0] = R.id.button15;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button button16 = findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DiscoveredOK[15] != 1 && ReturnedCard[0] != R.id.button16 && ReturnedCard[1] != R.id.button16) {
                    //Afficher le fond
                    button16.setBackgroundResource(ImageListe[15]);
                    //Incremente le nombre de cartes retournées
                    RevealedCards = RevealedCards + 1;
                    //Si on a une cartes révélées
                    if (RevealedCards == 1) {
                        //On enregistre la carte retournée
                        ReturnedCardImage[0] = ImageListe[15];
                        ReturnedCard[0] = R.id.button16;
                    }
                    //Si on a deux cartes révélées
                    else if (RevealedCards == 2) {
                        //On incrémente le nombre de tour
                        TurnNumber = TurnNumber + 1;
                        nbrTour.setText("" + TurnNumber);
                        //Si la carte précédente est la bonne
                        if (ReturnedCardImage[0] == ImageListe[15]) {
                            //On ré-initialise le nombre de cartes révélées
                            RevealedCards = 0;
                            DiscoveredOK[15] = 1;
                            Points = Points + 2;
                            PairDiscovered = PairDiscovered + 1;
                            if(PairDiscovered == 8) {Winning();}
                            nbrPoints.setText("" + Points);
                            for (int j = 0; j < 16; j++) {
                                if (ReturnedCardImage[0] == ImageListe[j]) {
                                    DiscoveredOK[j] = 1;
                                }
                            }
                        }
                        //On enregistre cette carte
                        else {
                            ReturnedCard[1] = R.id.button16;
                        }
                    }
                    //Si on a trois cartes révélées
                    else if (RevealedCards == 3) {
                        //On ré-initialise à 2 le nombre de cartes révélées
                        RevealedCards = 1;
                        //On masque les deux autres
                        Button button_1old = findViewById(ReturnedCard[0]);
                        button_1old.setBackgroundResource(R.color.colorPrimary);
                        Button button_2old = findViewById(ReturnedCard[1]);
                        button_2old.setBackgroundResource(R.color.colorPrimary);

                        ReturnedCardImage[0] = ImageListe[15];
                        ReturnedCard[0] = R.id.button16;
                        ReturnedCard[1] = 0;
                        Points = Points - 1;
                        nbrPoints.setText("" + Points);
                    }
                }
            }
        });

        final Button Restart = findViewById(R.id.RestartButton);
        Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheEnd();
            }
        });


    }

    void Winning() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Well Done !");
        alertDialog.setMessage("You finish the game with " + Points + " points !");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Play Again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        TheEnd();
                    }
                });
        alertDialog.show();
    }

    void TheEnd() {
        
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        final Button button5 = findViewById(R.id.button5);
        final Button button6 = findViewById(R.id.button6);
        final Button button7 = findViewById(R.id.button7);
        final Button button8 = findViewById(R.id.button8);
        final Button button9 = findViewById(R.id.button9);
        final Button button10 = findViewById(R.id.button10);
        final Button button11 = findViewById(R.id.button11);
        final Button button12 = findViewById(R.id.button12);
        final Button button13 = findViewById(R.id.button13);
        final Button button14 = findViewById(R.id.button14);
        final Button button15 = findViewById(R.id.button15);
        final Button button16 = findViewById(R.id.button16);

        final TextView nbrTour = findViewById(R.id.nbrTour);
        final TextView nbrPoints = findViewById(R.id.nbrPoints);

        TurnNumber = 0;
        nbrTour.setText("" + TurnNumber);
        Points = 0;
        nbrPoints.setText("" + Points);
        RevealedCards = 0;

        button1.setBackgroundResource(R.color.colorPrimary);
        button2.setBackgroundResource(R.color.colorPrimary);
        button3.setBackgroundResource(R.color.colorPrimary);
        button4.setBackgroundResource(R.color.colorPrimary);
        button5.setBackgroundResource(R.color.colorPrimary);
        button6.setBackgroundResource(R.color.colorPrimary);
        button7.setBackgroundResource(R.color.colorPrimary);
        button8.setBackgroundResource(R.color.colorPrimary);
        button9.setBackgroundResource(R.color.colorPrimary);
        button10.setBackgroundResource(R.color.colorPrimary);
        button11.setBackgroundResource(R.color.colorPrimary);
        button12.setBackgroundResource(R.color.colorPrimary);
        button13.setBackgroundResource(R.color.colorPrimary);
        button14.setBackgroundResource(R.color.colorPrimary);
        button15.setBackgroundResource(R.color.colorPrimary);
        button16.setBackgroundResource(R.color.colorPrimary);

        ReturnedCard[0] = 0;
        ReturnedCard[1] = 0;
        ReturnedCardImage[0] = 0;
        for (int i = 0; i < 16; i++) {
            DiscoveredOK[i] = 0;
        }

        shuffleArray(ImageListe);
    }

    static void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    void StartGame() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Nouvelle partie.");
        alertDialog.setMessage("Retrouvez les paires, marquez des points !\r\nUne bonne paire : +2 points\r\nUne mauvaies paire : -1 point");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Jouer",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
