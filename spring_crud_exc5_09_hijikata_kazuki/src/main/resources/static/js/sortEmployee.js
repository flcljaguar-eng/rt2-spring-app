/**
 * 従業員の並び替えを行うファイル
 */
function initialize() {
	//thタグがクリックされた場合sortRowsが呼び出される
	document.querySelectorAll("th").forEach(th => th.onclick = sortRows);
	document.querySelector("th").classList.add("sort-asc");
}


//ソート処理
function sortRows() {
	//テーブルの各行からその行への参照とクリックされた列の値をセットにしたレコードオブジェクトを生成
	const table = document.querySelector("table");
	//ソート用の配列にその要素を代入
	const records = [];
	for (let i = 1; i < table.rows.length; i++) {
		const record = {};
		record.row = table.rows[i];
		record.key = table.rows[i].cells[this.cellIndex].textContent;
		records.push(record);
	}
	//ソート用配列をソートする
	//thタグのCSSクラスによって昇順、降順の処理を分ける
	if (this.classList.contains("sort-asc")) {
		records.sort(compareKeysReverse);
		purgeSortMaker();
		this.classList.add("sort-desc");
	} else {
		records.sort(compareKeys);
		purgeSortMaker();
		this.classList.add("sort-asc");
	}

	//ソート後の順番で行をテーブルに書き出す
	for (let i = 0; i < records.length; i++) {
		//対象がテーブルの子要素だった場合消してから足される
		table.appendChild(records[i].row);
	}
}

//thのCSSクラスを削除する
function purgeSortMaker() {
	document.querySelectorAll("th").forEach(th => {
		th.classList.remove("sort-asc");
		th.classList.remove("sort-desc");
	});
}


//レコードオブジェクトからソート用の値を取り出し比較した結果を返すメソッド
//昇順
function compareKeys(a, b) {
	const aKey = Number(a.key);
	const bKey = Number(b.key);
	if (Number.isNaN(aKey)) {
		if (a.key < b.key) return -1;
		if (a.key > b.key) return 1;
		return 0;
	}
	if (aKey < bKey) return -1;
	if (aKey > bKey) return 1;
	return 0;

}
//降順
function compareKeysReverse(a, b) {
	const aKey = Number(a.key);
	const bKey = Number(b.key);
	if (Number.isNaN(aKey)) {
		if (a.key < b.key) return 1;
		if (a.key > b.key) return -1;
		return 0;
	}
	if (aKey < bKey) return 1;
	if (aKey > bKey) return -1;
	return 0;
}

initialize();