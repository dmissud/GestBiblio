# Creadted by DMI
  Feature: Prendre en compte un Emprunt

    Scenario: Prendre en compte l'emprunt d'un exemplaire d'ouvrage disponible par un adherent inscrit
      Given L'exemplaire avec le code "000000001" est disponible
      Given L'adherent "AD0000001" est connue de la Bibliotheque
      When l'adherent "AD0000001"  umprunte l'exemplaire "000000001"
      Then l'emprunt est cree
      And l'exemplaire n'est plus disponible
      And l'adherent dispose d'un emprunt en plus
