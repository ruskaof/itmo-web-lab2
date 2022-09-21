import { bindDataSendingButtons } from "./scripts/data_sending.js";
import { initializeCanvasGraph } from "./scripts/graph.js";
import { includeValidation } from "./scripts/validation.js";
import {BASE_URL, documentIDs} from "./scripts/contstants/constants.js";


document.addEventListener("DOMContentLoaded", () => {
    initializeCanvasGraph(BASE_URL)
    bindDataSendingButtons(() => {initializeCanvasGraph.updateDots()}, BASE_URL)
    includeValidation(documentIDs)
});