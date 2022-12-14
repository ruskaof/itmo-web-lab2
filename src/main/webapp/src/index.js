import {bindDataSendingButtons} from "./scripts/data_sending.js";
import {initializeCanvasGraph} from "./scripts/graph.js";
import {includeValidation} from "./scripts/validation.js";
import {BASE_URL, documentIDs} from "./scripts/contstants/constants.js";


document.addEventListener("DOMContentLoaded", () => {
    /* Update table data as soon as the page loads */
    fetch(BASE_URL + "/servlet-controller?table_html=1").then((response) => {
        return response.text()
    }).then((response_text) => {
        $("#" + "tbody").html(response_text);
    })

    initializeCanvasGraph(BASE_URL)
    bindDataSendingButtons(() => {
        initializeCanvasGraph.updateDots()
    }, BASE_URL)
    includeValidation(documentIDs)

    /* Remove unnecessary "code" query parameter that is there because of Vk auth */
    window.history.replaceState(null, null, window.location.pathname);
});