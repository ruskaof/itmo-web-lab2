export function bindDataSendingButtons(
    clickSentCallback,
    BASE_URL
) {
    $("#reset_button").on("click", () => {
        submitClickWithBody({
            clear: 1
        })
    })
    $("#submit_button").on("click", () => {
        console.log("button click")
        const formData = Object.fromEntries(new FormData(document.getElementById("form")).entries());

        submitClickWithBody({
            x: formData.x,
            y: formData.y,
            r: formData.r,
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

        submitClickWithBody({
            x: x,
            r: r,
            y: y
        })
    };

    /**
     * This method should be used to convert local canvas x value
     * to a correct math x value of the graph using the R value
     */
    function convertXToRadiusOf(x,  r) {
        return ((x - width / 2) / rValue) * r;
    }

    /**
     * This method should be used to convert local canvas y value
     * to a correct math x value of the graph using the R value
     */
    function convertYToRadiusOf(y, r) {
        return ((height - y - height / 2) / rValue) * r;
    }


    function submitClickWithBody(body) {
        fetch(BASE_URL + "/ServletController", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body),
        }).then((response) => {
            return response.text()
        }).then((response_text) => {
            $(function () {
                $("#" + "tbody").html(response_text);
            });
        }).then(() => {
            clickSentCallback()
        });
    }
}
