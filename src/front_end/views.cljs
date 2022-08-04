(ns front-end.views)

[:canvas {:width 500
          :height 500
          :id "cv"
          :style {:border "1px solid black" :display "block"}}]
[:button {:on-click #(re-frame.core/dispatch [:draw-grid (.getElementById js/document "Draw grid")])}]
