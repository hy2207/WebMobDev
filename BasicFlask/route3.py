from flask import Flask, render_template, request

app = Flask(__name__)


@app.route('/')                         # default method is GET
def init():                            # this is a comment. You can create your own function name
    return '<h1>Welcome to Flask! </h1>'

@app.route('/about', methods=['POST'])
def your_url():
    if request.method == 'POST':
        return render_template('about.html', name=request.form.get('dname', 'No data passed'))
        # request.form.get if method=POST
        # request.args.get if method=GET
    else:
        return 'This is not valid'


if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)   # host='0.0.0.0' means whatever your public ip assigned will be used
    
# to test: use RestMan extensions in chrome