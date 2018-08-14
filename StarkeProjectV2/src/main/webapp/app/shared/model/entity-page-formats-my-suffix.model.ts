export interface IEntityPageFormatsMySuffix {
    id?: number;
    pageformatid?: number;
    pageformatname?: string;
    mainimagepath?: string;
    image2path?: string;
    image3path?: string;
    entityPageDetailsId?: number;
}

export class EntityPageFormatsMySuffix implements IEntityPageFormatsMySuffix {
    constructor(
        public id?: number,
        public pageformatid?: number,
        public pageformatname?: string,
        public mainimagepath?: string,
        public image2path?: string,
        public image3path?: string,
        public entityPageDetailsId?: number
    ) {}
}
