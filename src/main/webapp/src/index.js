import { bindDataSendingButtons } from "./scripts/data_sending.js";
import { initializeCanvasGraph } from "./scripts/graph.js";
import { includeValidation } from "./scripts/validation.js";


const BASE_URL = "http://localhost:8080/lab2WildFly-1.0-SNAPSHOT"

document.addEventListener("DOMContentLoaded", () => {
    initializeCanvasGraph(BASE_URL)
    bindDataSendingButtons(() => {initializeCanvasGraph.updateDots()}, BASE_URL)
    includeValidation()
});