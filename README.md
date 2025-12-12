<h1><strong>RayTracer</strong> Java pour le cours de Conception Orientée Objet</h1>
<h2><i>Adam Darrot - Noam Labrousse</i></h2> <!-- Slightly smaller and italicized for subtle emphasis -->
<br>
<br>
<h2>Usage</h2>
<h3>$ java -jar raytracer.jar [options | scene]<h3>

<h3>Scene</h3>
<p>Chemin relatif du fichier de scène à utiliser</p>
<br>
<h3>Options :<h3> 
<ul>
    <li>
        <h4>--all</h4>
        <p>Génère les images de tous les fichiers .scene et .test présents dans le répertoire TestScenes. Il est nécessaire de se trouver dans le répertoire racine du repo</p>
    </li>
    <li>
        <h4>--compare image1 image2</h4>
        <p>Compare les deux images, donne le nombre de pixels différents et crée une image différentielle imageDiff.png</p>
    </li>
</ul>
<br>
<br>
<h2>Build :</h2>
<h3>$ mvn package</h3>
<h5>Le .jar se trouvera dans target/</h5>
<br>
<br>
<h2>Images Générées</h2>

![Final](final.png)
![Final avec bonus](final_avec_bonus.png)
