.. meta::
   :version: renaissance
   :lang: de
   :author: Michael Eichberg
   :keywords: Kontrollaufgaben, Polynomartihmetik
   :description lang=de: Kontrollaufgaben in Hinblick auf Polynomarithmetik im Körper GF(2^x)
   :id: vorlesung-endliche-koerper-kontrollaufgaben
   :first-slide: last-viewed
   :master-password: WirklichSchwierig!

.. include:: ../docutils.defs



Endliche Körper - Kontrollaufgaben
================================================================================

:Dozent: `Prof. Dr. Michael Eichberg <https://delors.github.io/cv/folien.de.rst.html>`__
:Kontakt: michael.eichberg@dhbw.de, Raum 149B
:Version: 1.0.1


.. class:: new-section

Rechnen mit Polynomen :math:`GF(2^x)`
---------------------------------------



.. class:: exercises

Klassenraumübung
------------------

.. exercise:: Polynomarithmetik im endlichen Körper GF(2⁷)

    Gegen sei das irreduzible Polynom: :math:`x^7 + x + 1`. Berechnen Sie jeweils das Ergebnis:

    1. Addition: :math:`(x^3 + x + 1)  + (x^4 + x)`
    2. Multiplikation: :math:`(x^3 + x + 1) \times (x^2 + 1)`
    3. Multiplikation: :math:`(x^6) \times (x^5)`
    4. Multiplikation: :math:`(x^5 + x^3) \times (x^4 + x + 1)`

    .. solution::
        :pwd: 2^7...

        .. rubric:: Lösung

        1. Ergebnis: :math:`(x^4 + x^3 + 1)`
        2. Ergebnis: :math:`(x^5 + x^3 + x^2 + x^3 + x + 1) = x^5 + x^2 + x + 1`
        3. Ergebnis: :math:`x^{11} \bmod (x^7 + x + 1) = (x^4 \times x^7) \bmod (x^7 + x + 1) = x^4 \times (x + 1) = x^5 + x^4`

           Hinweis: :math:`x + 1` ist einfach der Rest, der Division von :math:`x^7` durch unser irreduzibles Polynom.

           Alternativ: :math:`x^{11} / (x^7 + x + 1) = x^4 + \frac{(x^5 + x^4)}{x^7 + x + 1} \Rightarrow \text{d. h. der Rest ist } x^5 + x^4`
        4. Ergebnis:

           :1. Schritt: :math:`(x^5 + x^3)\times(x^4 + x + 1) = x^9 + x^6 + x^5 + x^7 + x^4 + x^3`
           :2. Schritt: Ergebnis aus Schritt 1 modulo :math:`x^7 + x + 1`.

               (Zur Erinnerung: :math:`(a + b) \bmod m = ((a \bmod m) + (b \bmod m)) \bmod m`)

               Wir rechnen somit:

               1. :math:`x^9 \bmod (x^7 + x + 1) = (x^2 \times x^7) \bmod (x^7 + x + 1) = x^2\times(x + 1) = x^3 + x^2`.
               2. :math:`x^7 \bmod (x^7 + x + 1) = 1\times(x + 1) = x + 1`.
               3. Verrechnung aller Ergebnisse:

                  :math:`(x^9 + x^7 + x^6 + x^5 + x^4 + x^3 \equiv (x^3 + x^2) + (x + 1) + x^6 + x^5 + x^4 + x^3`

                  :math:`= x^6 + x^5 + x^4 + x^2 + x + 1`
