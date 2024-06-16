## Examen sur les Concepts de Programmation Orientée Objet (POO)

### Instructions Générales

Pour cet examen, vous allez analyser le code fourni pour un projet de visualisation de l'algorithme KMeans. Vous devrez identifier et décrire les composants POO du code en Python. Ensuite, vous allez transformer la structure générale de ce code en Java, sans implémenter les méthodes. Vous devrez simplement écrire la structure des classes et les signatures des méthodes.

### Partie 1 : Analyse du Code Python (50%)

**Questions :**

1. **Identifiez la classe principale dans le code fourni.**
   - Quel est le nom de cette classe ?
   - Quelles sont les méthodes définies dans cette classe ?

2. **Constructeur de la classe :**
   - Quel est le rôle du constructeur (`__init__`) dans cette classe ?
   - Quelles propriétés sont initialisées dans le constructeur ?

3. **Méthodes de la classe :**
   - Nommez et décrivez brièvement chaque méthode de la classe.
   - Quelle est la fonction de chaque méthode ?

4. **Héritage et Widgets :**
   - De quelle classe la classe principale hérite-t-elle ?
   - Quels widgets sont créés dans la méthode `create_widgets` ?

### Partie 2 : Transformation en Java (50%)

**Instructions :**

Transformez la structure générale de la classe et les signatures des méthodes du code Python en Java. Ne mettez pas en œuvre les méthodes, mais définissez seulement la structure.

**Code Python fourni :**

```python
import tkinter as tk
from tkinter import ttk
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from matplotlib.animation import FuncAnimation
import random

class KMeansVisualizer(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("K-Means Visualization")
        self.geometry("800x600")

        self.n_points = 200
        self.n_clusters = 4
        self.colors = ["#3498db", "#2ecc71", "#f1c40f", "#9b59b6", "#e74c3c"]

        self.points = self.generate_points(self.n_points)
        self.centroids = self.initialize_centroids(self.n_clusters)
        self.point_colors = ['grey'] * self.n_points

        self.create_widgets()
        self.update_plot()

    def create_widgets(self):
        control_frame = ttk.Frame(self)
        control_frame.pack(side=tk.TOP, fill=tk.X)

        ttk.Label(control_frame, text="Number of Points:").pack(side=tk.LEFT, padx=5)
        self.n_points_var = tk.IntVar(value=self.n_points)
        ttk.Entry(control_frame, textvariable=self.n_points_var, width=5).pack(side=tk.LEFT, padx=5)

        ttk.Label(control_frame, text="Number of Clusters:").pack(side=tk.LEFT, padx=5)
        self.n_clusters_var = tk.IntVar(value=self.n_clusters)
        ttk.Entry(control_frame, textvariable=self.n_clusters_var, width=5).pack(side=tk.LEFT, padx=5)

        ttk.Button(control_frame, text="Start", command=self.start_clustering).pack(side=tk.LEFT, padx=5)

        self.figure, self.ax = plt.subplots()
        self.canvas = FigureCanvasTkAgg(self.figure, master=self)
        self.canvas.get_tk_widget().pack(side=tk.TOP, fill=tk.BOTH, expand=True)

        self.canvas.mpl_connect('button_press_event', self.on_click)

    def generate_points(self, n):
        return np.random.rand(n, 2)

    def initialize_centroids(self, k):
        indices = random.sample(range(len(self.points)), k)
        return self.points[indices]

    def update_plot(self):
        self.ax.clear()
        self.ax.scatter(self.points[:, 0], self.points[:, 1], c=self.point_colors, s=30)
        self.ax.scatter(self.centroids[:, 0], self.centroids[:, 1], c='red', s=100, marker='X')
        self.canvas.draw()

    def start_clustering(self):
        self.n_points = self.n_points_var.get()
        self.n_clusters = self.n_clusters_var.get()
        self.points = self.generate_points(self.n_points)
        self.centroids = self.initialize_centroids(self.n_clusters)
        self.point_colors = ['grey'] * self.n_points
        self.iteration = 0
        self.animation = FuncAnimation(self.figure, self.kmeans_step, interval=500, repeat=False)
        self.canvas.draw()

    def kmeans_step(self, frame):
        if self.iteration < 10:
            clusters = self.assign_clusters()
            new_centroids = self.update_centroids(clusters)
            self.centroids = new_centroids
            self.update_plot()
            self.iteration += 1
        else:
            self.animation.event_source.stop()

    def assign_clusters(self):
        distances = np.sqrt(((self.points - self.centroids[:, np.newaxis])**2).sum(axis=2))
        cluster_indices = np.argmin(distances, axis=0)
        self.point_colors = [self.colors[i] for i in cluster_indices]
        return cluster_indices

    def update_centroids(self, clusters):
        new_centroids = np.array([self.points[clusters == k].mean(axis=0) for k in range(self.n_clusters)])
        return new_centroids

    def on_click(self, event):
        if event.inaxes != self.ax:
            return
        self.points = np.vstack([self.points, [event.xdata, event.ydata]])
        self.point_colors.append('grey')
        self.update_plot()

if __name__ == "__main__":
    app = KMeansVisualizer()
    app.mainloop()
```

**Structure Java :**

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class KMeansVisualizer extends JFrame {
    private int n_points;
    private int n_clusters;
    private Color[] colors;
    private Point[] points;
    private Point[] centroids;
    private Color[] pointColors;

    public KMeansVisualizer() {
        super("K-Means Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.n_points = 200;
        this.n_clusters = 4;
        this.colors = new Color[]{Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.RED};

        this.points = generatePoints(this.n_points);
        this.centroids = initializeCentroids(this.n_clusters);
        this.pointColors = new Color[this.n_points];

        createWidgets();
        updatePlot();
    }

    private void createWidgets() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel pointsLabel = new JLabel("Number of Points:");
        controlPanel.add(pointsLabel);

        JTextField pointsField = new JTextField(5);
        controlPanel.add(pointsField);

        JLabel clustersLabel = new JLabel("Number of Clusters:");
        controlPanel.add(clustersLabel);

        JTextField clustersField = new JTextField(5);
        controlPanel.add(clustersField);

        JButton startButton = new JButton("Start");
        controlPanel.add(startButton);

        add(controlPanel, BorderLayout.NORTH);
    }

    private Point[] generatePoints(int n) {
        // Implémentation
        return new Point[n];
    }

    private Point[] initializeCentroids(int k) {
        // Implémentation
        return new Point[k];
    }

    private void updatePlot() {
        // Implémentation
    }

    private void startClustering() {
        // Implémentation
    }

    private void kmeansStep() {
        // Implémentation
    }

    private int[] assignClusters() {
        // Implémentation
        return new int[this.n_points];
    }

    private Point[] updateCentroids(int[] clusters) {
        // Implémentation
        return new Point[this.n_clusters];
    }

    private void onClick(MouseEvent event) {
        // Implémentation
    }

    public static void main(String[] args) {
        KMeansVisualizer app = new KMeansVisualizer();
        app.setVisible(true);
    }
}
```

### Bon travail !
