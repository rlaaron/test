package edu.usfca.ds.views;

import edu.usfca.ds.shapes.DSShapeLink;
import edu.usfca.ds.shapes.DSShapeNullPointer;
import edu.usfca.ds.shapes.DSShapeSingleLL2R;
import edu.usfca.xj.appkit.gview.object.GElement;
import edu.usfca.xj.appkit.gview.object.GElementLabel;
import edu.usfca.xj.appkit.gview.object.GLink;

import java.awt.*;


public class DSViewGraph extends DSView {

    public final static int RANDOMIZE = 1;
    public final static int VIEWLOGICAL = 2;
    public final static int VIEWINTERNALLIST = 3;
    public final static int VIEWINTERNALARRAY = 4;
    public final static int CHANGETOLARGE = 5;
    public final static int CHANGETOSMALL = 6;

    protected double big_edge_percent = 0.7;


    public int SMALL_LOGICAL_X_SHIFT = 0;
    public int LARGE_LOGICAL_X_SHIFT = 0;


    public  int SMALL_MATRIX_WIDTH = 40;
    public  int SMALL_MATRIX_HEIGHT = 40;
    public  int SMALL_MATRIX_INITIAL_X = 580;
    public  int SMALL_MATRIX_INITIAL_Y = 70;

    public  int LARGE_MATRIX_WIDTH = 25;
    public  int LARGE_MATRIX_HEIGHT = 25;
    public  int LARGE_MATRIX_INITIAL_X = 440;
    public  int LARGE_MATRIX_INITIAL_Y = 10;

    public  int matrix_width = SMALL_MATRIX_WIDTH;
    public  int matrix_height = SMALL_MATRIX_HEIGHT;
    public  int matrix_initial_x = SMALL_MATRIX_INITIAL_X;
    public  int matrix_initial_y = SMALL_MATRIX_INITIAL_Y;


    public  int SMALLSIZE = 6;
    public  int LARGESIZE = 18;


    public  int DELTA_X_L_SMALL = 75;
    public  int DELTA_Y_L_SMALL = 40;

    public  int ELEM_WIDTH_SMALL = DELTA_X_L_SMALL - 20;
    public  int ELEM_HEIGHT_SMALL = DELTA_Y_L_SMALL - 5;


    public  int INITIAL_X_L_SMALL = 600;
    public  int INITIAL_Y_L_SMALL = 70;

    public  int DELTA_X_L_LARGE = 65;
    public  int DELTA_Y_L_LARGE = 24;

    public  int ELEM_WIDTH_LARGE = DELTA_X_L_LARGE - 20;
    public  int ELEM_HEIGHT_LARGE = DELTA_Y_L_LARGE - 4;


    public  int deltaXL = DELTA_X_L_SMALL;
    public  int deltaYL = DELTA_Y_L_SMALL;

    public  int elem_width = ELEM_WIDTH_SMALL;
    public  int elem_height = ELEM_HEIGHT_SMALL;

    public  int INITIAL_Y_L_LARGE = 20;
    public  int INITIAL_X_L_LARGE = 550;



    public final int NUMSTEPS = 40;

    public int size = SMALLSIZE;


    protected DSShapeSingleLL2R EdgesL[][];

    protected DSShapeSingleLL2R list[][];
    protected DSShapeNullPointer listH[];
    protected GElement index[];

    protected GElement nodes[];
    protected DSShapeLink edges[][];
    protected int cost[][];
    protected int Xpos[];
    protected int XposL[];
    protected int Ypos[];
    protected int YposL[];
    protected int edgeFlattness [][];
    protected int flatness[][];
    protected int listLength[];
    protected GElementLabel listindex[];
    protected GElement matrix[][];
    protected GElementLabel matrixindex[][];

    protected int incompatable[][][];

    protected int viewingType = VIEWLOGICAL;

    public DSViewGraph() {
        super();

        createLabel("", 1, 1);

        create_small_graph();


        randomize();
    }


    void setEdge(int i, int j, DSShapeLink l) {
        edges[i][j] = l;
        edges[j][i] = l;
    }

    protected void CallFunction(int function) {
        switch (function) {
            case RANDOMIZE:
                randomize();
                break;
            case VIEWLOGICAL:
                switch_to_logical();
                break;
            case VIEWINTERNALLIST:
                switch_to_internal_list();
                break;
            case VIEWINTERNALARRAY:
                switch_to_internal_array();
                break;
                case CHANGETOLARGE:
                switch_to_large_graph();
                break;
                case CHANGETOSMALL:
                switch_to_small_graph();
        }

    }


    protected void create_small_graph() {

        int i;

        size = SMALLSIZE;

        Xpos = new int[]{705, 620, 780, 620, 780, 705};
        Ypos = new int[]{60, 170, 170, 300, 300, 410};
        for (i=0; i<size;i++)
            Xpos[i] = Xpos[i] + SMALL_LOGICAL_X_SHIFT;
        XposL = new int[size];
        YposL = new int[size];
        list = new DSShapeSingleLL2R[size][];
        listH = new DSShapeNullPointer[size];
        for (i = 0; i < size; i++) {
            list[i] = new DSShapeSingleLL2R[size];
        }
        for (i = 0; i < size; i++) {
            XposL[i] = INITIAL_X_L_SMALL + deltaXL * i;
            YposL[i] = INITIAL_Y_L_SMALL + deltaYL * i;
        }

        EdgesL = new DSShapeSingleLL2R[size][];
        for (i=0; i<size;i++)
            EdgesL[i] = new DSShapeSingleLL2R[size];
        flatness = new int[][]{{0, -1, 1, -200, 200, -50},
                               {1, 0, 1, -1, 50, -200},
                               {-1, -1, 0, 50, 1, 200},
                               {200, 1, -50, 0, -1, -1},
                               {-200, -50, -1, 1, 0, 1},
                               {50, 200, -200, 1, -1, 0}};

        nodes = new GElement[size];
        edges = new DSShapeLink[size][];
        cost = new int[size][];
        for (i = 0; i < size; i++) {
            edges[i] = new DSShapeLink[size];
            cost[i] = new int[size];
        }
        listLength = new int[size];
        listindex = new GElementLabel[size];

        matrix = new GElement[size][];
        matrixindex = new GElementLabel[size][];
        for (i = 0; i < size; i++) {
            matrix[i] = new GElement[size];
            matrixindex[i] = new GElementLabel[2];
        }

        randomize();

    }

    protected void create_large_graph() {

        int i;

        size = LARGESIZE;

        Xpos = new int[]{550,650,750,850,
                         600, 700, 800,
                                 550,655,750,850,
                                 600, 700, 800,
                                 550,650,750,850};
        Ypos = new int[]{100,100, 100, 100,
                         175,175,175,
                         250,250,250,250,
                         325,325,325,
                         400,400,400,400};

        for (i=0; i<size;i++)
            Xpos[i] = Xpos[i] + LARGE_LOGICAL_X_SHIFT;

        XposL = new int[size];
        YposL = new int[size];
        list = new DSShapeSingleLL2R[size][];
        listH = new DSShapeNullPointer[size];
        for (i = 0; i < size; i++) {
            list[i] = new DSShapeSingleLL2R[size];
        }
        for (i = 0; i < size; i++) {
            XposL[i] = INITIAL_X_L_LARGE + deltaXL * i;
            YposL[i] = INITIAL_Y_L_LARGE + deltaYL * i;
        }

        EdgesL = new DSShapeSingleLL2R[size][];
        for (i=0; i<size;i++)
            EdgesL[i] = new DSShapeSingleLL2R[size];
        flatness = new int[][]{{   0,   1, 70, 130,  1,  0,  0, -5,  0,  0,  0,  0,  0,  0, -80,  0,   0,    0},
                               {  -1,   0,  1,  70, -1,  1,  0,  0,  1,  0,  0,  0,  0,  0,   0,  0,   0,    0},
                               { -70,  -1,  0,   1,  0, -1,  1,  0,  0,  1,  0,  0,  0,  0,   0,  0,   0,    0},
                               {-130, -70, -1,   0,  0,  0, -1,  0,  0,  0,  1,  0,  0,  0,   0,  0,   0,   80},
                               {  -1,   1,  0,   0,  0,  1,  0, -1,  1,  0,  0,  1,  0,  0,   0,  0,   0,    0},
                               {   0,  -1,  1,   0, -1,  0,  1,  0, -1,  1,  0,  0,  1,  0,   0,  0,   0,    0},
                               {   0,   0, -1,   1,  0, -1,  0,  0,  0, -1,  1,  0,  0,  1,   0,  0,   0,    0},
                               {   1,   0,  0,   0, -1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  -5,  0,   0,    0},
                               {   0,  -1,  0,   0,  1,  1,  0, -1,  0,  1,  0, -1,  1,  0,   0,  1,   0,    0},
                               {   0,   0,  0,   0,  0, -1, -1,  0,  1,  0,  1,  0, -1,  1,   0,  0,   1,    0},
                               {   0,   0,  0,  -1,  0,  0,  1,  0,  0,  1,  0,  0,  0, -1,   0,  0,   0,    1},
                               {   0,   0,  0,   0,  0,  0,  0,  1, -1,  0,  0,  0,  1,  0,  -1,  1,   0,    0},
                               {   0,   0,  0,   0,  0,  0,  0,  0,  1,  1,  0,  1,  0,  1,   0, -1,   1,    0},
                               {   0,   0,  0,   0,  0,  0,  0,  0,  0, -1,  0,  0,  0,  0,   0,  0,  -1,    1},
                               {  80,   0,  0,   0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,   0,  1, -40, -130},
                               {   0,   0,  0,   0,  0,  0,  0,  0,  0,  0,  0, -1,  1,  0,  -1,  0,   1,  -40},
                               {   0,   0,  0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  1,  40, -1,   0,    1},
                               {   0,   0,  0, -80,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1, 130, 40,  -1,    0}};

        incompatable = new int[][][] {{{1,8},{4,5}},{{2,9},{5,6}},{{4,11},{7,8}},{{5,12},{8,9}},{{6,13},{9,10}},
                                      {{8,15},{11,12}},{{9,16},{12,13}},{{0,2},{1,3}},{{14,16},{15,17}}};


        nodes = new GElement[size];
        edges = new DSShapeLink[size][];
        cost = new int[size][];
        for (i = 0; i < size; i++) {
            edges[i] = new DSShapeLink[size];
            cost[i] = new int[size];
        }
        listLength = new int[size];
        listindex = new GElementLabel[size];

        matrix = new GElement[size][];
        matrixindex = new GElementLabel[size][];
        for (i = 0; i < size; i++) {
            matrix[i] = new GElement[size];
            matrixindex[i] = new GElementLabel[2];
        }

        randomize();

    }


    boolean compatable(int u, int v) {
        int i;

        for (i=0; i<incompatable.length; i++) {
            if (((incompatable[i][0][0] == u && incompatable[i][0][1] == v) ||
                 (incompatable[i][0][1] == u && incompatable[i][0][0] == v)) &&
                (cost[incompatable[i][1][0]][incompatable[i][1][1]] < Integer.MAX_VALUE))
                return false;
            if (((incompatable[i][1][0] == u && incompatable[i][1][1] == v) ||
                 (incompatable[i][1][1] == u && incompatable[i][1][0] == v)) &&
                (cost[incompatable[i][0][0]][incompatable[i][0][1]] < Integer.MAX_VALUE))
                return false;
        }
        return true;
    }



    protected void switch_to_large_graph() {
        removeOld();
        matrix_width = LARGE_MATRIX_WIDTH;
        matrix_height = LARGE_MATRIX_HEIGHT;
        matrix_initial_x = LARGE_MATRIX_INITIAL_X;
        matrix_initial_y = LARGE_MATRIX_INITIAL_Y;
        deltaXL = DELTA_X_L_LARGE;
        deltaYL = DELTA_Y_L_LARGE;
        elem_width = ELEM_WIDTH_LARGE;
        elem_height = ELEM_HEIGHT_LARGE;

        create_large_graph();

    }

    protected void switch_to_small_graph() {
        removeOld();
        matrix_width = SMALL_MATRIX_WIDTH;
        matrix_height = SMALL_MATRIX_HEIGHT;
        matrix_initial_x = SMALL_MATRIX_INITIAL_X;
        matrix_initial_y = SMALL_MATRIX_INITIAL_Y;

        deltaXL = DELTA_X_L_SMALL;
        deltaYL = DELTA_Y_L_SMALL;
        elem_width = ELEM_WIDTH_SMALL;
        elem_height = ELEM_HEIGHT_SMALL;

        create_small_graph();

    }

    protected void switch_to_internal_list() {
        removeOld();
        viewingType = VIEWINTERNALLIST;
        BuildList();
    }

    protected void switch_to_logical() {
        removeOld();
        viewingType = VIEWLOGICAL;
        BuildLogical();
    }


    protected void removeOld() {
        int i, j;
        if (viewingType == VIEWLOGICAL) {
            for (i = 0; i < size; i++)
                for (j = 0; j < size; j++) {
                    removeLink(nodes[i], nodes[j]);
                    setEdge(i, j, null);
                }
            for (i = 0; i < size; i++)
                removeAny(nodes[i]);
        } else if (viewingType == VIEWINTERNALLIST) {
            for (i = 0; i < size; i++) {
                if (listLength[i] > 0) {
                    removeLink(listH[i], list[i][0]);
                    for (j = 1; j < listLength[i]; j++) {
                        removeLink(list[i][j - 1], list[i][j]);
                        removeAny(list[i][j - 1]);
                    }
                    removeAny(list[i][listLength[i] - 1]);


                }
                removeAny(listindex[i]);
                removeAny(listH[i]);
            }

        } else if (viewingType == VIEWINTERNALARRAY) {
            for (i = 0; i < size; i++) {
                removeAny(matrixindex[i][0]);
                removeAny(matrixindex[i][1]);
                for (j = 0; j < size; j++) {
                    removeAny(matrix[i][j]);
                }
            }
        } else {
            /* SHOULDN'T BE ABLE TO GET HERE! */
        }
    }


    protected void BuildLogical() {
        int i, j;
        for (i = 0; i < size; i++) {
            nodes[i] = createCircle(String.valueOf(i), Xpos[i], Ypos[i]);
            nodes[i].setLabelColor(Color.BLUE);
        }

        for (i = 0; i < size; i++) {
            for (j = i + 1; j < size; j++) {
                if (cost[i][j] < Integer.MAX_VALUE) {
                    setEdge(i, j, createLink(nodes[i], nodes[j], GLink.SHAPE_ARC, GElement.ANCHOR_CENTER, GElement.ANCHOR_CENTER,
                                             String.valueOf(cost[i][j]), flatness[i][j]));
                    edges[i][j].setArrowVisible(false);


                }
            }
        }

    }


    protected void randomize() {
        removeOld();

        if (size == SMALLSIZE) {
            randomize_small();
        } else {
            randomize_large();
        }
    }


    int find(int indepset[], int v) {
        if (indepset[v] < 0)
            return v;
        return find(indepset, indepset[v]);
    }

    boolean combine(int indepset[], int u, int v) {
        int upar = find(indepset, u);
        int vpar = find(indepset, v);

        if (upar != vpar) {
            if (indepset[upar] < indepset[vpar]) {
                indepset[upar] += indepset[vpar];
                indepset[vpar] = upar;
            } else {
                indepset[vpar] += indepset[upar];
                indepset[upar] = vpar;

            }
            return true;
        }  else {
            return false;
        }
    }

    protected void randomize_large() {
        int i,j;
        int numtrees = size;

        int indepset[] = new int[size];
        for (i=0;i<size;i++)
            indepset[i] = -1;

        for (i=0; i<size;i++) {
            for (j=i; j<size; j++) {
                if (flatness[i][j] != 0 && compatable(i,j) && Math.random() < big_edge_percent)  {
                    cost[i][j] = (int) (Math.random() * 9 + 1);
                    cost[j][i] = cost[i][j];
                    if (combine(indepset,i,j)) numtrees--;
                }
                else {
                    cost[i][j] = Integer.MAX_VALUE;
                    cost[j][i] = cost[i][j];
                }

            }
        }

        while (numtrees > 1) {
            int u = (int) (Math.random()*18);
            int v = (int) (Math.random()*18);
            if ((flatness[u][v] != 0) && compatable(u,v)) {
                if (combine(indepset,u,v)) {
                    cost[u][v] = (int) (Math.random() * 9 + 1);
                    cost[v][u] = cost[u][v];
                    numtrees--;
                }
            }

        }


        build_graph();

    }


    protected void build_graph() {
        if (viewingType == VIEWLOGICAL) {
            BuildLogical();
        } else if (viewingType == VIEWINTERNALARRAY) {
            BuildArray();
        } else if (viewingType == VIEWINTERNALLIST) {
            BuildList();
        }

    }



    protected void randomize_small() {
        int i, j;

        for (i = 0; i < size; i++)
            for (j = 0; j < size; j++)
                cost[i][j] = Integer.MAX_VALUE;
        int perm[] = new int[]{0, 1, 2, 3, 4, 5};
        for (i = 0; i < size; i++) {
            int swapindex = (int) (Math.random() * (size - i));
            int tmp = perm[i];
            perm[i] = perm[swapindex + i];
            perm[swapindex + i] = tmp;
        }
        for (i = 1; i < size; i++) {
            cost[perm[i - 1]][perm[i]] = (int) (Math.random() * 9 + 1);
            cost[perm[i]][perm[i - 1]] = cost[perm[i - 1]][perm[i]];
        }
        for (i = 0; i < size; i++) {
            for (j = i + 1; j < size; j++) {
                if (Math.random() < 0.5) {
                    cost[i][j] = (int) (Math.random() * 9 + 1);
                    cost[j][i] = cost[i][j];

                }
            }
        }
        if ((cost[1][2] < Integer.MAX_VALUE || cost[3][4] < Integer.MAX_VALUE) && cost[0][5] < Integer.MAX_VALUE) {
            cost[0][5] = Integer.MAX_VALUE;
            cost[5][0] = Integer.MAX_VALUE;
            cost[3][5] = (int) (Math.random() * 9+1);
            cost[5][3] = cost[3][5];
        }

       build_graph();

    }

    protected void BuildArray() {
        int i, j;
        String middlelab;
        for (i = 0; i < size; i++) {
            matrixindex[i][0] = createLabel(String.valueOf(i), matrix_initial_x, matrix_height * (i + 1) + matrix_initial_y,false);
            matrixindex[i][0].setLabelColor(Color.BLUE);
            matrixindex[i][1] = createLabel(String.valueOf(i), matrix_initial_x + matrix_width * (i + 1), matrix_initial_y,false);
            matrixindex[i][1].setLabelColor(Color.BLUE);
            for (j = 0; j < size; j++) {
                if (cost[i][j] == Integer.MAX_VALUE)
                    middlelab = "inf";
                else
                    middlelab = String.valueOf(cost[i][j]);
                matrix[i][j] = createRectangle(middlelab, matrix_width * (j + 1) + matrix_initial_x,
                                               matrix_height * (i + 1) + matrix_initial_y, matrix_width, matrix_height,false);
            }
        }
    }

    protected void BuildList() {
        int i, j;

        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++)
                list[i][j] = null;
        }

        for (i = 0; i < size; i++) {
            listH[i] = createNullPointer(XposL[0] - deltaXL, YposL[i], elem_height, elem_height,false);
            listindex[i] = createLabel(String.valueOf(i), XposL[0] - deltaXL - elem_height, YposL[i],false);
            listindex[i].setLabelColor(Color.BLUE);
            listH[i].setNull(true);
            listLength[i] = 0;
            for (j = 0; j < size && cost[i][j] == Integer.MAX_VALUE; j++)
                EdgesL[i][j] = null;
            if (j < size) {
                listH[i].setNull(false);
                list[i][listLength[i]] = createSingleLinkedListRec2R(String.valueOf(j), String.valueOf(cost[i][j]), XposL[listLength[i]], YposL[i], elem_width, elem_height);//    list[i][j] = createSingleLinkedListRec2R()
                list[i][listLength[i]].setPointerVoid(true);
                GLink l = createLink(listH[i], list[i][listLength[i]], GLink.SHAPE_ARC, GElement.ANCHOR_RIGHT, GElement.ANCHOR_LEFT, "", 0);
                l.setSourceOffset(-elem_height / 2, 0);
                list[i][listLength[i]].setLabelColor(Color.BLUE);
                EdgesL[i][j] = list[i][listLength[i]];
                listLength[i]++;
                j++;

            }
            while (j < size) {
                while (j < size) {
                    if (cost[i][j] < Integer.MAX_VALUE)
                        break;
                    EdgesL[i][j] = null;
                    j++;
                }
                if (j < size) {
                    list[i][listLength[i]] = createSingleLinkedListRec2R(String.valueOf(j), String.valueOf(cost[i][j]), XposL[listLength[i]], YposL[i], elem_width, elem_height);//    list[i][j] = createSingleLinkedListRec2R()
                    list[i][listLength[i]].setPointerVoid(true);
                    GLink l = createLink(list[i][listLength[i] - 1], list[i][listLength[i]], GLink.SHAPE_ARC, GElement.ANCHOR_RIGHT, GElement.ANCHOR_LEFT, "", 0);
                    l.setSourceOffset(-elem_width * .25 * .5, 0);
                    list[i][listLength[i] - 1].setPointerVoid(false);
                    list[i][listLength[i]].setLabelColor(Color.BLUE);
                    EdgesL[i][j] = list[i][listLength[i]];
                    j++;
                    listLength[i]++;

                }
            }


        }


    }


    protected void switch_to_internal_array() {
        removeOld();
        viewingType = VIEWINTERNALARRAY;
        BuildArray();
    }


}
