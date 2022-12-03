# Creadted by DMI
  Feature: Prendre en compte un Emprunt

    Scenario: Prendre en compte l'emprunt d'un exemplaire d'ouvrage disponible par un adherent inscrit
      Given l'exemplaire avec le code "EX00001" est disponible
      Given l'adherent "AD00001" est connue de la Bibliotheque
      When l'adherent "AD00001"  umprunte l'exemplaire "EX00001"
      Then l'emprunt de l'exemplaire "EX00001" par l'adherent "AD00001" existe
      And l'exemplaire "EX00001" n'est plus disponible
      And l'adherent "AD00001" a emprunte "EX00001"

