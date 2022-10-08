export function bindDataSendingButtons(
    clickSentCallback,
    BASE_URL
) {
    $("#reset_button").on("click", () => {
        submitClickWithParameters({
            clear: 1
        })
    })
    $("#submit_button").on("click", () => {
        const formData = Object.fromEntries(new FormData(document.getElementById("form")).entries());

        submitClickWithParameters({
            x: formData.x,
            y: formData.y,
            r: formData.r,
            round_if_needed: 1
        })
    })

    const canvas = (document.getElementById("graph"));
    const width = canvas.width;
    const height = canvas.height;
    const rValue = width / 2.5

    canvas.onmousedown = function (event) {
        const form = document.getElementById("form");
        const formData = new FormData(form);

        const r = parseFloat(formData.get("r").toString());
        const x = convertXToRadiusOf(event.offsetX, r);
        const y = convertYToRadiusOf(event.offsetY, r);

        submitClickWithParameters({
            x: x,
            r: r,
            y: y,
            round_if_needed: 0
        })
    };

    /**
     * This method should be used to convert local canvas x value
     * to a correct math x value of the graph using the R value
     */
    function convertXToRadiusOf(x, r) {
        return ((x - width / 2) / rValue) * r;
    }

    /**
     * This method should be used to convert local canvas y value
     * to a correct math x value of the graph using the R value
     */
    function convertYToRadiusOf(y, r) {
        return ((height - y - height / 2) / rValue) * r;
    }


    function submitClickWithParameters(parameters) {
        fetch(BASE_URL + "/ServletController?" + new URLSearchParams(parameters), {
            method: "GET",
        }).then(() => {
            return fetch(BASE_URL + "/ServletController?table_html=1")
        }).then((response) => {
            return response.text()
        }).then((response_text) => {
            $("#" + "tbody").html(response_text);
        }).then(() => {
            clickSentCallback()
        }).catch(() => {
            alert("There was a network error. Please check your internet connection.")
        });
    }
}
