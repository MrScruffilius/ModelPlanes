import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MediumPlane {

    private final static int SIZE = 150;
    private final static int COLOR_AMOUNT = 13;
    private final static int BACK_AMOUNT = 11;
    private final static int WINGS_AMOUNT = 11;
    private final static int FRONT_AMOUNT = 12;
    private final static int FRAME_AMOUNT = 13;


    public static void createPic(String genome) throws IOException {

        String path = "";
        switch (genome.substring(0, 2)) {
            case "01" -> path = "Resources\\comm.png";
            case "02" -> path = "Resources\\unco.png";
            case "03" -> path = "Resources\\rare.png";
            case "04" -> path = "Resources\\epic.png";
            case "05" -> path = "Resources\\legn.png";
        }
        File file = new File(path);


        BufferedImage img = ImageIO.read(file);
        int[][] pixel = new int[SIZE][SIZE];
        Raster raster = img.getData();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int[] rgb = new int[5];
                raster.getPixel(i, j, rgb);
                pixel[i][j] = (rgb[0] << 16) ^ (rgb[1] << 8) ^ (rgb[2]);
            }
        }


        boolean[][] muster = new boolean[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                muster[i][j] = false;
            }
        }

        String path2 = "Resources\\frame_medium_" + Integer.parseInt(genome.substring(8, 10), 16) + ".png";
        File file2 = new File(path2);


        BufferedImage img2 = ImageIO.read(file2);
        Raster raster2 = img2.getData();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int[] rgb = new int[5];
                raster2.getPixel(i, j, rgb);
                int temp = (rgb[0] << 16) ^ (rgb[1] << 8) ^ (rgb[2]);
                if (temp != 16711935) {
                    pixel[i][j] = temp;
                    muster[i][j] = true;
                }
            }
        }


        String path3 = "Resources\\wings_medium_" + Integer.parseInt(genome.substring(6, 8), 16) + ".png";
        File file3 = new File(path3);


        BufferedImage img3 = ImageIO.read(file3);
        Raster raster3 = img3.getData();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int[] rgb = new int[5];
                raster3.getPixel(i, j, rgb);
                int temp = (rgb[0] << 16) ^ (rgb[1] << 8) ^ (rgb[2]);
                if (temp != 16711935) {
                    pixel[i][j] = temp;
                    muster[i][j] = true;
                }

            }
        }


        String path4 = "Resources\\back_medium_" + Integer.parseInt(genome.substring(4, 6), 16) + ".png";
        File file4 = new File(path4);


        BufferedImage img4 = ImageIO.read(file4);
        Raster raster4 = img4.getData();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int[] rgb = new int[5];
                raster4.getPixel(i, j, rgb);
                int temp = (rgb[0] << 16) ^ (rgb[1] << 8) ^ (rgb[2]);
                if (temp != 16711935) {
                    pixel[i][j] = temp;
                    muster[i][j] = true;
                }

            }
        }


        String path5 = "Resources\\front_medium_" + Integer.parseInt(genome.substring(10, 12), 16) + ".png";
        File file5 = new File(path5);


        BufferedImage img5 = ImageIO.read(file5);
        Raster raster5 = img5.getData();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int[] rgb = new int[5];
                raster5.getPixel(i, j, rgb);
                int temp = (rgb[0] << 16) ^ (rgb[1] << 8) ^ (rgb[2]);
                if (temp != 16711935) {
                    pixel[i][j] = temp;
                    muster[i][j] = true;
                }

            }
        }


        String path6 = "Resources\\color" + Integer.parseInt(genome.substring(2, 4), 16) + ".png";

        File file6 = new File(path6);


        BufferedImage img6 = ImageIO.read(file6);
        Raster raster6 = img6.getData();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int[] rgb = new int[5];
                raster6.getPixel(i, j, rgb);
                int temp = (rgb[0] << 16) ^ (rgb[1] << 8) ^ (rgb[2]);
                if (pixel[i][j] == 16711680 && muster[i][j]) {
                    pixel[i][j] = temp;
                }

            }
        }


        BufferedImage theImage = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                theImage.setRGB(i, j, pixel[i][j]);
            }
        }

        File outputfile = new File(genome + ".png");
        try {
            ImageIO.write(theImage, "png", outputfile);
        } catch (IOException ignored) {
        }
    }

    public static String createGenomeMedium() {
        StringBuilder builder = new StringBuilder();
        builder.append("01");

        int randomColor = (int) (Math.random() * COLOR_AMOUNT);
        builder.append(toHex(randomColor + 1));
        int randomBack = (int) (Math.random() * BACK_AMOUNT);
        builder.append(toHex(randomBack + 1));
        int randomWings = (int) (Math.random() * WINGS_AMOUNT);
        builder.append(toHex(randomWings + 1));
        int randomFrame = (int) (Math.random() * FRAME_AMOUNT);
        builder.append(toHex(randomFrame + 1));
        int randomFront = (int) (Math.random() * FRONT_AMOUNT);
        builder.append(toHex(randomFront + 1));
        builder.append("02");

        try {
            Files.writeString(Path.of("Resources\\pop.txt"), builder + "\n", StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }

        try {
            createPic(builder.toString());
        } catch (Exception ignored) {
        }

        return builder.toString();
    }

    public static String mergeGenomesMedium() throws IOException {

        List<String> liste = Files.readAllLines(Path.of("Resources\\pop.txt")).stream().filter(s -> s.length() > 8).toList();
        int poss = (int) (Math.random() * liste.size());
        String genome1 = liste.get(poss);
        int poss2 = (int) (Math.random() * liste.size());
        while (poss2 == poss) {
            poss2 = (int) (Math.random() * liste.size());
        }
        String genome2 = liste.get(poss2);


        StringBuilder builder = new StringBuilder();

        int possi = (int) (Math.random() * 100);
        if (possi < 1) {
            builder.append("05");
        } else if (possi < 4) {
            builder.append("04");
        } else if (possi < 14) {
            builder.append("03");
        } else if (possi < 39) {
            builder.append("02");
        } else {
            builder.append("01");
        }


        //Color modifier
        possi = (int) (Math.random() * 100);
        if (possi < 40) {
            builder.append(genome1, 2, 4);
        } else if (possi < 80) {
            builder.append(genome2, 2, 4);
        } else {
            int randomColor = (int) (Math.random() * COLOR_AMOUNT);
            builder.append(toHex(randomColor + 1));
        }


        possi = (int) (Math.random() * 100);
        if (possi < 40) {
            builder.append(genome1, 4, 6);
        } else if (possi < 80) {
            builder.append(genome2, 4, 6);
        } else {
            int randomBack = (int) (Math.random() * BACK_AMOUNT);
            builder.append(toHex(randomBack + 1));
        }


        possi = (int) (Math.random() * 100);
        if (possi < 40) {
            builder.append(genome1, 6, 8);
        } else if (possi < 80) {
            builder.append(genome2, 6, 8);
        } else {
            int randomWings = (int) (Math.random() * WINGS_AMOUNT);
            builder.append(toHex(randomWings + 1));
        }


        possi = (int) (Math.random() * 100);
        if (possi < 40) {
            builder.append(genome1, 8, 10);
        } else if (possi < 80) {
            builder.append(genome2, 8, 10);
        } else {
            int randomFrame = (int) (Math.random() * FRAME_AMOUNT);
            builder.append(toHex(randomFrame + 1));

        }


        possi = (int) (Math.random() * 100);
        if (possi < 40) {
            builder.append(genome1, 10, 12);
        } else if (possi < 80) {
            builder.append(genome2, 10, 12);
        } else {
            int randomFront = (int) (Math.random() * FRONT_AMOUNT);
            builder.append(toHex(randomFront + 1));

        }

        builder.append("02");

        try {
            Files.writeString(Path.of("Resources\\pop.txt"), builder + "\n", StandardOpenOption.APPEND);
        } catch (Exception ignored) {
        }


        try {
            createPic(builder.toString());
        } catch (Exception ignored) {
        }

        return builder.toString();
    }

    private static String toHex(int i) {
        String s = Integer.toHexString(i);
        if (s.length() == 1) {
            return "0" + s;
        }
        return s;
    }

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {
            createGenomeMedium();
        }
    }
}
